plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.cognifide.aem.bundle")
}

apply(from = rootProject.file("gradle/common.gradle.kts"))

group = "com.company.example.aem"
description = "Example - AEM Common"

aem {
    tasks {
        bundleCompose {
            // TODO / embedPackage("org.hashids", true, "org.hashids:hashids:1.0.1")
        }
        packageCompose {
            fromJar("org.jsoup:jsoup:1.10.2")
            fromJar("com.github.mickleroy:aem-sass-compiler:1.0.1")
        }
    }
}
