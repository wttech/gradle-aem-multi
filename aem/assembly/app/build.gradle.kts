plugins {
    id("com.cognifide.aem.package")
}

description = "Example - AEM Application"

aem {
    tasks {
        compose {
            fromProject(":aem:common")
            fromProject(":aem:sites")
        }
    }
}

