plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.cognifide.aem.bundle")
}

description = "Example - AEM Application Author"

dependencies {
    implementation(project(":aem:app.common"))
    implementation(project(":aem:app.core"))
}

aem {
    bundle {
        installRunMode = "author"
    }
}

