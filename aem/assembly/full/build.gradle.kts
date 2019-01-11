
plugins {
    id("com.cognifide.aem.package")
}

description = "Example - AEM Full"

aem {
    tasks {
        compose {
            fromProject(":aem:common")
            fromProject(":aem:sites")

            fromProject(":aem:content.demo")
            fromProject(":aem:content.init")
        }
    }
}
