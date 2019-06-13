plugins {
    id("com.cognifide.aem.package")
}

description = "Example - AEM Application"

aem {
    tasks {
        packageCompose {
            fromProject(":aem:common")
            fromProject(":aem:sites")
        }
    }
}

