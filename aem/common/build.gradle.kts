plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.cognifide.aem.bundle")
}

description = "Example - AEM Common"

aem {
    tasks {
        bundle {
            embedPackage("org.hashids", true, "org.hashids:hashids:1.0.1")
        }
        compose {
            fromJar("org.jsoup:jsoup:1.10.2")
            fromJar("com.github.mickleroy:aem-sass-compiler:1.0.1")
        }
    }
}
