import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.cognifide.aem.common")
    id("nebula.integtest-standalone")
}

apply(from = rootProject.file("gradle/common.gradle.kts"))

description = "Example - Integration Tests"

dependencies {
    "integTestImplementation"(project(":aem:common"))
    "integTestImplementation"(project(":aem:sites"))

    "integTestRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine:5.3.2")
    "integTestImplementation"("org.junit.jupiter:junit-jupiter-api:5.3.2")
    "integTestImplementation"("org.jsoup:jsoup:1.12.1")

    "integTestImplementation"("org.jetbrains.kotlin:kotlin-stdlib:${Build.KOTLIN_VERSION}")
    "integTestImplementation"("com.intuit.karate:karate-core:0.9.4")
    "integTestImplementation"("com.intuit.karate:karate-apache:0.9.4")
    "integTestImplementation"("com.intuit.karate:karate-junit5:0.9.4")
    "integTestImplementation"("net.masterthought:cucumber-reporting:4.9.0")

}

tasks {
    named<Test>("integrationTest") {
        outputs.upToDateWhen { false }

        testLogging {
            showStandardStreams = true
            events = setOf(TestLogEvent.PASSED, TestLogEvent.FAILED, TestLogEvent.SKIPPED)
        }

        systemProperty("karate.options", aem.props.string("karate.options") ?: "")
        systemProperty("karate.env", aem.props.string("karate.env") ?: aem.env)
        systemProperty("karate.config.dir", "src/integTest/kotlin/karate")

        systemProperty("test.publishUrl", aem.props.string("test.publishUrl") ?: aem.main.environment.hosts.publish.url)
        systemProperty("test.parallel", aem.props.string("test.parallel") ?: "")
    }
}

sourceSets {
    named("integTest") {
        resources {
            srcDir(file("src/integTest/kotlin"))
            exclude("**/*.kt")
        }
    }
}
