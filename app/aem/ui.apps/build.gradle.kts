plugins {
    id("com.cognifide.aem.package")
}

apply(from = rootProject.file("app/common.gradle.kts"))

group = "com.company.example.aem"
description = "Example - UI Apps"

aem {
    tasks {
        packageCompose {
            dependsOn(":app:aem:ui.frontend:webpack")
            fromProject(":app:aem:core")
        }
    }
}
