plugins {
    id("com.neva.fork")
    id("com.cognifide.aem.config")
}

description = "Example"
defaultTasks("develop")

aem {
    tasks {
        sequence("develop", {
            description = "Builds and deploys applications, runs migration scripts then integration and functional tests"
        }) {
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
