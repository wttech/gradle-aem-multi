plugins {
    id("com.neva.fork")
    id("com.cognifide.aem.common")
}

apply(from = "gradle/common.gradle.kts")
apply(from = "gradle/fork.gradle.kts")

description = "Example"
defaultTasks("develop")

aem {
    tasks {
        registerSequence("develop", {
            description = "Builds and deploys AEM application to instances, cleans environment then runs all tests"
        }) {
            if (!props.flag("setup.skip")) {
                dependsOn(":aem:instanceSetup")
            }
            dependsOn(":aem:assembly:full:packageDeploy")
            if (!props.flag("migration.skip")) {
                dependsOn(":aem:migration:packageDeploy")
            }
            dependsOn(
                    ":aem:environmentReload",
                    ":aem:environmentAwait"
            )
            if (!props.flag("test.skip")) {
                dependsOn(
                        ":test:integration:test",
                        ":test:functional:run",
                        ":test:performance:lighthouseRun"
                )
            }
        }
    }
}
