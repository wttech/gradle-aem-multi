plugins {
    id("com.cognifide.aem.package")
}

description = "Example - AEM Full"

aem {
    tasks {
        packageCompose {
            fromProject(":aem:common")
            fromProject(":aem:sites")

            fromProject(":aem:site.demo")
            fromProject(":aem:site.live")
        }
    }
}
