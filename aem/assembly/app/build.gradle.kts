plugins {
    id("com.cognifide.aem.package")
}

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

