import com.neva.gradle.fork.ForkExtension

configure<ForkExtension> {
    properties {
        define(mapOf(
                "projectGroup" to { validator { shouldNotContain("projectName") } },

                "aemInstanceType" to { select("local", "remote") },
                "aemInstanceRunModes" to { text("nosamplecontent") },
                "aemInstanceJvmOpts" to { text("-server -Xmx1024m -XX:MaxPermSize=256M -Djava.awt.headless=true") },
                "aemInstanceAuthorHttpUrl" to { url("http://localhost:4502") },
                "aemInstancePublishHttpUrl" to { url("http://localhost:4503") }
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
