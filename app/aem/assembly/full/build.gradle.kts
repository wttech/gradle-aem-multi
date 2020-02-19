plugins {
    id("com.cognifide.aem.package")
}

apply(from = rootProject.file("app/common.gradle.kts"))
apply(from = rootProject.file("app/aem/common.gradle.kts"))

description = "Example - AEM Full"

aem {
    tasks {
        packageCompose {
            fromSubpackage(":app:aem:ui.apps:packageCompose")
            fromSubpackage(":app:aem:ui.content:packageCompose")
        }
    }
}
