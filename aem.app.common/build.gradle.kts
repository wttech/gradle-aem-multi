import com.cognifide.gradle.aem.pkg.ComposeTask

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.cognifide.aem.bundle")
}

description = "Example - AEM Application Common"

aem {
    bundle {
        embedPackage("org.hashIds", false, "org.hashids:hashids:1.0.1")
        exportPackage("org.hashids")
    }
}

tasks.named<ComposeTask>("aemCompose") {
    fromJar("org.jsoup:jsoup:1.10.2")
    fromJar("com.github.mickleroy:aem-sass-compiler:1.0.1")
}
