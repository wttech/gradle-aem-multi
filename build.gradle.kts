plugins {
    id("com.neva.fork")
    id("com.cognifide.aem.base")
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
                "/com/company/aem/example" to "/{{projectGroup|substitute('.', '/')}}/{{projectName}}",
                "/example" to "/{{projectName}}"
        ))
        replaceContents(mapOf(
                "com.company.aem.example" to "{{projectGroup}}.{{projectName}}",
                "com.company.aem" to "{{projectGroup}}",
                "Example" to "{{projectLabel}}",
                "example" to "{{projectName}}"
        ))
    }
}

apply(from = "gradle/common.gradle.kts")
