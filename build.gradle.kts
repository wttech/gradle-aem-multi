plugins {
    id("com.neva.fork")
    id("com.cognifide.aem.config")
}

description = "Example"
defaultTasks = listOf("deploy")

aem {
    tasks {
        sequence("deploy") {
            dependsOn(
                ":aem:aemSatisfy",
                ":aem:full:aemDeploy",
                ":aem:migration:aemDeploy",
                ":test:integration:test",
                ":test:functional:test"
            )
        }
    }
}

fork {
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

apply(from = "gradle/common.gradle.kts")
