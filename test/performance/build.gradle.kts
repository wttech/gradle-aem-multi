plugins {
    id("com.cognifide.aem.common")
    id("com.cognifide.lighthouse")
}

apply(from = rootProject.file("gradle/common.gradle.kts"))

description = "Example - Performance Tests"

lighthouse {
    baseUrl = aem.props.string("test.publishUrl") ?: aem.main.environment.hosts.publish.url
}
