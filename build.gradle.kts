import com.neva.gradle.fork.ForkTask

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
                pathed(":aem:aemSatisfy"),
                pathed(":aem:full:aemDeploy"),
                pathed(":aem:migration:aemDeploy"),
                pathed(":test:integration:test"),
                pathed(":test:functional:test")
            )
        }
    }
}

tasks {
    named<ForkTask>(ForkTask.NAME).configure {
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
}

apply(from = "gradle/common.gradle.kts")
