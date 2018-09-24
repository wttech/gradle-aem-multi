import com.cognifide.gradle.aem.api.AemExtension

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.cognifide.aem.bundle")
}

description = "Example - AEM Application Common"

configure<AemExtension> {
    bundle {
        exportPackage("org.hashids")
    }
}

dependencies {
    aemInstall("org.jsoup:jsoup:1.10.2")
    aemInstall("com.github.mickleroy:aem-sass-compiler:1.0.1")
    aemEmbed("org.hashids:hashids:1.0.1")
}
