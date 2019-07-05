plugins {
    id("com.neva.fork")
    id("com.cognifide.aem.common")
}

description = "Example"
defaultTasks("develop")

aem {
    tasks {
        sequence("develop", {
            description = "Builds and deploys AEM application, cleans environment then runs integration and functional tests"
        }) {
            dependsOrdered(
                    ":aem:instanceSatisfy",
                    ":aem:assembly:full:packageDeploy",
                    ":aem:environmentClean",
                    ":aem:environmentCheck",
                    ":test:integration:test",
                    ":test:functional:test",
                    ":test:performance:test"
            )
        }
    }
}

apply(from = "gradle/fork.gradle.kts")
apply(from = "gradle/common.gradle.kts")
