plugins {
    id("com.cognifide.aem.package")
}

apply(from = rootProject.file("gradle/common.gradle.kts"))

group = "com.company.example.aem"
description = "Example - AEM Full"

aem {
    tasks {
        packageCompose {
            fromSubpackage(":aem:common:packageCompose")
            fromSubpackage(":aem:sites:packageCompose")
            fromSubpackage(":aem:site.demo:packageCompose")
            fromSubpackage(":aem:site.live:packageCompose")
        }
    }
}
