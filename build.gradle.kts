plugins {
    id("base")
    id("com.neva.fork")
    id("com.cognifide.common")
    id("net.researchgate.release")
}

apply(from = "gradle/fork/fork.gradle.kts")
apply(from = "gradle/fork/props.gradle.kts")

description = "Example"
defaultTasks("develop")

common {
    tasks {
        registerSequence("develop", {
            description = "Builds and deploys AEM application to instances, cleans environment then runs all tests"
            dependsOn(":requireProps")
        }) {
            when (prop.string("instance.type")) {
                "local" -> {
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
                                ":test:functional:runTests",
                                ":test:performance:lighthouseRun"
                        )
                    }
                }
                else -> {
                    if (!prop.flag("setup.skip")) {
                        dependsOn(":env:instanceProvision")
                    }
                    dependsOn(":app:aem:all:packageDeploy")
                    if (!prop.flag("migration.skip")) {
                        dependsOn(":app:aem:migration:packageDeploy")
                    }
                    if (!prop.flag("test.skip")) {
                        dependsOn(
                                ":test:integration:integrationTest",
                                ":test:functional:runTests",
                                ":test:performance:lighthouseRun"
                        )
                    }
                }
            }
        }
    }
}

tasks {
    val publishToInternal = register("publishToInternal") {
        dependsOn(
                ":app:aem:all:publishMavenPublicationToInternalRepository",
                ":app:aem:core:publishMavenPublicationToInternalRepository"
        )
    }
    afterReleaseBuild {
        dependsOn(publishToInternal)
    }
}
