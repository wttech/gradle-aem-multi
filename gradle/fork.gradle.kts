import com.neva.gradle.fork.ForkExtension

configure<ForkExtension> {
    properties {
        define(mapOf(
                "projectName" to { description = "Artifact 'name' coordinate (lowercase)" },
                "projectLabel" to { description = "Nice project name (human-readable)" },
                "projectGroup" to {
                    validator { javaPackage(); notContains("projectName") }
                    description = "Java package in source code and artifact 'group' coordinate"
                },
                "aemInstanceType" to {
                    select("local", "remote")
                    description = "Local - instance will be created on local file system.\nRemote - connecting to remote instance only."
                },
                "aemInstanceRunModes" to { text("nosamplecontent") },
                "aemInstanceJvmOpts" to { text("-server -Xmx1024m -XX:MaxPermSize=256M -Djava.awt.headless=true") },
                "aemInstanceAuthorHttpUrl" to {
                    url("http://localhost:4502")
                    description = "URL for accessing AEM author instance"
                },
                "aemInstancePublishHttpUrl" to {
                    url("http://localhost:4503")
                    description = "URL for accessing AEM publish instance"
                }
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
