plugins {
    id("com.cognifide.aem.instance")
    id("com.cognifide.aem.environment")
}

apply(from = "gradle/environment.gradle.kts")
apply(from = "gradle/artifacts.gradle.kts")
