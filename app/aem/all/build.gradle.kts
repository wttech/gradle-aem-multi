plugins {
    id("com.cognifide.aem.package")
}

apply(from = rootProject.file("app/common.gradle.kts"))
apply(from = rootProject.file("app/aem/common.gradle.kts"))

description = "Example - AEM All-In-One Package"

aem {
    tasks {
        packageCompose {
            nestPackage(":app:aem:ui.apps:packageCompose")
            nestPackage(":app:aem:ui.content:packageCompose")
        }
    }
}
