import com.neva.gradle.fork.ForkExtension

configure<ForkExtension> {
    properties {
        define(mapOf(
                "projectName" to {
                    description = "Artifact 'name' coordinate (lowercase)"
                    validator { lowercased(); alphanumeric() }
                },
                "projectLabel" to { description = "Nice project name (human-readable)" },
                "projectGroup" to {
                    description = "Java package in source code and artifact 'group' coordinate"
                    validator { javaPackage(); notEndsWith("projectName") }
                },
                "aemInstanceAuthorHttpUrl" to {
                    url("http://localhost:4502")
                    optional()
                    description = "URL for accessing AEM author instance"
                },
                "aemInstancePublishHttpUrl" to {
                    url("http://localhost:4503")
                    optional()
                    description = "URL for accessing AEM publish instance"
                },
                "aemInstanceType" to {
                    select("local", "remote")
                    description = "local - instance will be created on local file system\nremote - connecting to remote instance only"
                    controller { toggle(value == "local", "aemInstanceRunModes", "aemInstanceJvmOpts", "aemLocalInstance*") }
                },
                "aemInstanceRunModes" to { text("nosamplecontent") },
                "aemInstanceJvmOpts" to { text("-server -Xmx1024m -XX:MaxPermSize=256M -Djava.awt.headless=true") },
                "aemLocalInstanceJarUri" to {},
                "aemLocalInstanceLicenseUri" to {},
                "aemLocalInstanceZipUri" to { optional() }
        ))
    }
    config {
        cloneFiles()
        moveFiles(mapOf(
                "/com/company/example/aem" to "/{{projectGroup|substitute('.', '/')}}/{{projectName}}/aem",
                "/example" to "/{{projectName}}"
        ))
        replaceContents(mapOf(
                "com.company.example.aem" to "{{projectGroup}}.{{projectName}}.aem",
                "com.company.example" to "{{projectGroup}}.{{projectName}}",
                "Example" to "{{projectLabel}}",
                "example" to "{{projectName}}"
        ))
    }
}
