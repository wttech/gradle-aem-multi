plugins {
    id("com.neva.fork")
    id("com.cognifide.aem.config")
}

description = "Example"
defaultTasks("deploy")

aem {
    tasks {
        sequence("deploy") {
            dependsOn(
                ":aem:instanceSatisfy",
                ":aem:assembly:full:packageDeploy",
                ":aem:environmentClean",
                ":aem:migration:packageDeploy",
                ":test:integration:test",
                ":test:functional:test"
            )
        }
    }
}

apply(from = "gradle/fork.gradle.kts")
apply(from = "gradle/common.gradle.kts")
