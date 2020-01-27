plugins {
    id("com.cognifide.aem.package")
}

apply(from = rootProject.file("gradle/common.gradle.kts"))

group = "com.company.example.aem"
description = "Example - UI Apps"

aem {
    tasks {
        packageCompose {
            dependsOn(":aem:ui.frontend:webpack")
            fromProject(":aem:core")
        }
    }
}
