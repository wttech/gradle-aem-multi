plugins {
    id("com.cognifide.aem.package")
}

apply(from = rootProject.file("gradle/common.gradle.kts"))

group = "com.company.example.aem"
description = "Example - AEM Full"

aem {
    tasks {
        packageCompose {
            fromSubpackage(":aem:ui.apps:packageCompose")
            fromSubpackage(":aem:ui.content:packageCompose")
        }
    }
}
