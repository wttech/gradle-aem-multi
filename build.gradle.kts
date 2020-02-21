plugins {
    id("com.neva.fork")
    id("com.cognifide.common")
}

apply(from = "gradle/fork/fork.gradle.kts")
apply(from = "gradle/fork/props.gradle.kts")

description = "Example"
defaultTasks("develop")

common {
    tasks {
        registerSequence("develop", {
            description = "Builds and deploys AEM application to instances, cleans environment then runs all tests"
        }) {
            if (!prop.flag("setup.skip")) {
                dependsOn(
                        ":env:instanceSetup",
                        ":env:environmentUp"
                )
            }
            dependsOn(":app:aem:all:packageDeploy")
            if (!prop.flag("migration.skip")) {
                dependsOn(":app:aem:migration:packageDeploy")
            }
            dependsOn(
                    ":env:environmentReload",
                    ":env:environmentAwait"
            )
            if (!prop.flag("test.skip")) {
                dependsOn(
                        ":test:integration:integrationTest",
                        ":test:functional:generateReport",
                        ":test:performance:lighthouseRun"
                )
            }
        }
    }
}
