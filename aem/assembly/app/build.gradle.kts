plugins {
    id("com.cognifide.aem.package")
}

apply(from = rootProject.file("gradle/common.gradle.kts"))

group = "com.company.example.aem"
description = "Example - AEM Application"

aem {
    tasks {
        packageCompose {
            fromProject(":aem:common")
            fromProject(":aem:sites")
        }
    }
}

