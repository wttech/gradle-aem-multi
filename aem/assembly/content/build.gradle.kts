plugins {
    id("com.cognifide.aem.package")
}

description = "Example - AEM Content"

aem {
    tasks {
        compose {
            fromProject(":aem:content.demo")
            fromProject(":aem:content.init")
        }
    }
}
