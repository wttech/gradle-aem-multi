plugins {
    id("com.cognifide.aem.common")
    id("com.cognifide.lighthouse")
}

apply(from = rootProject.file("gradle/common.gradle.kts"))

description = "Example - Performance Tests"

lighthouse {
    baseUrl = aem.prop.string("test.publishUrl")
}

tasks {
    lighthouseRun {
        mustRunAfter(":aem:environmentAwait")
    }
}
