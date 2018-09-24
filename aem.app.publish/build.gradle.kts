plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.cognifide.aem.bundle")
}

description = "Example - AEM Application Publish"

dependencies {
    implementation(project(":aem.app.common"))
    implementation(project(":aem.app.core"))
}


