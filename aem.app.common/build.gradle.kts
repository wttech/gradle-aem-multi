import com.cognifide.gradle.aem.pkg.tasks.Compose

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.cognifide.aem.bundle")
}

description = "Example - AEM Application Common"

aem {
    bundle {
        embedPackage("org.hashids", true, "org.hashids:hashids:1.0.1")
    }
}

tasks.named<Compose>(Compose.NAME) {
    fromJar("org.jsoup:jsoup:1.10.2")
    fromJar("com.github.mickleroy:aem-sass-compiler:1.0.1")
}
