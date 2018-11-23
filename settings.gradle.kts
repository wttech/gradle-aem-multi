// Plugins configuration

pluginManagement {
    repositories {
        mavenLocal()
        jcenter()
        maven { url = uri("https://plugins.gradle.org/m2/") }
        maven { url = uri("http://dl.bintray.com/cognifide/maven-public") }
        maven { url = uri("https://dl.bintray.com/neva-dev/maven-public") }
        maven { url = uri("https://dl.bintray.com/kotlin/kotlin-dev") } // TODO use stable
    }
}

// Project structure

rootProject.name = "example"

include("aem.app.common")
include("aem.app.core")
include("aem.app.author")
include("aem.app.publish")
include("aem.app.config")
include("aem.app.design")
include("aem.app.full")

include("aem.content.init")
include("aem.content.demo")

include("aem.full")
include("aem.migration")

include("test.functional")
include("test.integration")
