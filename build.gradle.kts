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

apply(from = "gradle/fork.gradle.kts")
apply(from = "gradle/common.gradle.kts")
