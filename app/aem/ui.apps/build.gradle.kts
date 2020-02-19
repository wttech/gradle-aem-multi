plugins {
    id("com.cognifide.aem.package")
}

apply(from = rootProject.file("app/common.gradle.kts"))
apply(from = rootProject.file("app/aem/common.gradle.kts"))

description = "Example - UI Apps"

aem {
    tasks {
        packageCompose {
            dependsOn(":app:aem:ui.frontend:webpack")
            fromProject(":app:aem:core")
        }
    }
}
