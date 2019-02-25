plugins {
    id("com.cognifide.aem.package")
}

description = "Example - AEM Content"

aem {
    tasks {
        compose {
            fromProject(":aem:site.demo")
            fromProject(":aem:site.live")
        }
    }
}
