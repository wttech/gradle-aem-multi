plugins {
    id("com.cognifide.aem.package")
}

apply(from = rootProject.file("app/common.gradle.kts"))

group = "com.company.example.aem"
description = "Example - AEM Full"

aem {
    tasks {
        packageCompose {
            fromSubpackage(":app:aem:ui.apps:packageCompose")
            fromSubpackage(":app:aem:ui.content:packageCompose")
        }
    }
}
