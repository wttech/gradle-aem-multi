import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.cognifide.aem.common")
}

apply(from = rootProject.file("gradle/common.gradle.kts"))

description = "Example - Integration Tests"

dependencies {
    testImplementation(project(":aem:common"))
    testImplementation(project(":aem:sites"))

    testImplementation("org.jetbrains.kotlin:kotlin-stdlib:${Build.KOTLIN_VERSION}")
    testImplementation("org.jsoup:jsoup:1.12.1")
    testImplementation("com.intuit.karate:karate-core:0.9.4")
    testImplementation("com.intuit.karate:karate-apache:0.9.4")
    testImplementation("com.intuit.karate:karate-junit5:0.9.4")
    testImplementation("net.masterthought:cucumber-reporting:4.9.0")
}

aem {
    tasks {
        named<Jar>(JavaPlugin.JAR_TASK_NAME) {
            enabled = false
        }

        named<Task>(LifecycleBasePlugin.CHECK_TASK_NAME) {
            dependsOn.remove(named(JavaPlugin.TEST_TASK_NAME))
        }

        named<Test>(JavaPlugin.TEST_TASK_NAME) {
            outputs.upToDateWhen { false }

            testLogging {
                showStandardStreams = true
                events = setOf(TestLogEvent.PASSED, TestLogEvent.FAILED, TestLogEvent.SKIPPED)
            }

            systemProperty("karate.options", props.string("karate.options") ?: "")
            systemProperty("karate.env", props.string("karate.env") ?: "local")
            systemProperty("karate.config.dir", "src/test/kotlin/karate")

            systemProperty("test.publishUrl", props.string("test.publishUrl") ?: main.environment.hosts.publish.url)
            systemProperty("test.parallel", props.string("test.parallel") ?: "")
        }
    }
}


sourceSets {
    test {
        resources {
            srcDir(file("src/test/kotlin"))
            exclude("**/*.kt")
        }
    }
}
