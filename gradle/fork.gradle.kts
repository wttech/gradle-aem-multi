import com.cognifide.gradle.aem.common.instance.local.Source
import com.neva.gradle.fork.ForkExtension
import java.io.File

configure<ForkExtension> {
    properties {
        define(mapOf(
                "sourcePath" to { enabled = false },
                "targetPath" to { enabled = false },
                "projectName" to {
                    description = "Artifact 'name' coordinate (lowercase)"
                    validator { lowercased(); alphanumeric() }
                    controller { other("targetPath").value = File(File(other("sourcePath").value).parentFile, value).toString() }
                    defaultValue = "example"
                },
                "projectLabel" to {
                    description = "Nice project name (human-readable)"
                    defaultValue = "Example"
                },
                "projectGroup" to {
                    description = "Java package in source code and artifact 'group' coordinate"
                    validator { javaPackage(); notEndsWith("projectName") }
                    defaultValue = "com.company"
                },
                "instanceAuthorHttpUrl" to {
                    url("http://localhost:4502")
                    optional()
                    description = "URL for accessing AEM author instance"
                },
                "instancePublishHttpUrl" to {
                    url("http://localhost:4503")
                    optional()
                    description = "URL for accessing AEM publish instance"
                },
                "instanceType" to {
                    select("local", "remote")
                    description = "local - instance will be created on local file system\nremote - connecting to remote instance only"
                    controller { toggle(value == "local", "instanceRunModes", "instanceJvmOpts", "localInstance*") }
                },
                "instanceRunModes" to { text("local,nosamplecontent") },
                "instanceJvmOpts" to { text("-server -Xmx2048m -XX:MaxPermSize=512M -Djava.awt.headless=true") },
                "localInstanceSource" to {
                    description = "Controls how instances will be created (from scratch, backup or automatically determined)"
                    select(Source.values().map { it.name.toLowerCase() }, Source.AUTO.name.toLowerCase())
                },
                "localInstanceQuickstartJarUri" to {
                    description = "Quickstart JAR (cq-quickstart-x.x.x.jar)"
                },
                "localInstanceQuickstartLicenseUri" to {
                    description = "Quickstart license file (license.properties)"
                },
                "localInstanceBackupDownloadUri" to {
                    description = "URL to backup file (SMB/SFTP/HTTP)"
                    optional()
                },
                "localInstanceBackupUploadUri" to {
                    description = "URL to backup directory (SMB/SFTP)"
                    optional()
                },
                "companyUser" to {
                    description = "User authorized to access AEM files"
                    defaultValue = System.getProperty("user.name").orEmpty()
                    optional()
                },
                "companyPassword" to {
                    description = "Password for user authorized to access AEM files"
                    optional()
                },
                "companyDomain" to {
                    description = "Needed only when accessing AEM files over SMB"
                    defaultValue = System.getenv("USERDOMAIN").orEmpty()
                    optional()
                }
        ))
    }
    config {
        textFiles.addAll(listOf("**/*.conf", "**/*.any"))
        cloneFiles()
        moveFiles(mapOf(
                "/com/company/example/aem" to "/{{projectGroup|substitute('.', '/')}}/{{projectName}}/aem", // TODO simplify
                "/example" to "/{{projectName}}",
                "/demo.example" to "/demo.{{projectName}}",
                "example.com" to "{{projectName}}.com"
        ))
        replaceContents(mapOf(
                "com.company.example.aem" to "{{projectGroup}}.{{projectName}}.aem",
                "com.company.example" to "{{projectGroup}}.{{projectName}}",
                "com.company" to "{{projectGroup}}",
                "Example" to "{{projectLabel}}",
                "example" to "{{projectName}}"
        ))
        copyTemplateFile("README.MD")
        removeFiles(listOf(
                "LICENSE",
                "azure-pipelines.yml",
                "gh-md-toc",
                "docs/*",
                "gradle/fork/*",
                "gradle/fork.gradle.kts"
        ))
        removeTexts(listOf(
                """    maven { url = uri("https://dl.bintray.com/neva-dev/maven-public") }""" + "\n",
                """    implementation("com.neva.gradle:fork-plugin:4.1.3")""" + "\n",
                """    id("com.neva.fork")""" + "\n",
                """apply(from = "gradle/fork.gradle.kts")""" + "\n"
        ))
    }
}
