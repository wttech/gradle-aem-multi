import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.cognifide.aem.common")
    id("nebula.integtest-standalone")
}

apply(from = rootProject.file("test/common.gradle.kts"))

description = "Example - Integration Tests"

dependencies {
    "integTestImplementation"(project(":app:aem:core"))

    "integTestRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine:5.3.2")
    "integTestImplementation"("org.junit.jupiter:junit-jupiter-api:5.3.2")
    "integTestImplementation"("org.jsoup:jsoup:1.12.1")

    "integTestImplementation"("org.jetbrains.kotlin:kotlin-stdlib:1.3.71")
    "integTestImplementation"("com.intuit.karate:karate-core:0.9.4")
    "integTestImplementation"("com.intuit.karate:karate-apache:0.9.4")
    "integTestImplementation"("com.intuit.karate:karate-junit5:0.9.4")
    "integTestImplementation"("net.masterthought:cucumber-reporting:4.9.0")

}

tasks {
    named<Test>("integrationTest") {
        mustRunAfter(":env:environmentAwait")
        outputs.upToDateWhen { false }

        testLogging {
            showStandardStreams = true
            events = setOf(TestLogEvent.PASSED, TestLogEvent.FAILED, TestLogEvent.SKIPPED)
        }
        systemProperties(mapOf<String, Any?>(
                "karate.options" to (aem.prop.string("karate.options") ?: ""),
                "karate.env" to (aem.prop.string("karate.env") ?: aem.commonOptions.env),
                "karate.config.dir" to "src/integTest/kotlin/karate",
                "test.publishUrl" to aem.prop.string("test.publishUrl")!! /* TODO ?: aem.projectMain.environment.hosts["publish"].url)*/,
                "test.parallel" to (aem.prop.string("test.parallel") ?: "")
        ))
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
