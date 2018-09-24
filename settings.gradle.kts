// Plugins configuration

pluginManagement {
    repositories {
        mavenLocal()
        jcenter()
        maven { url = uri("https://plugins.gradle.org/m2/") }
        maven { url = uri("http://dl.bintray.com/cognifide/maven-public") }
        maven { url = uri("https://dl.bintray.com/neva-dev/maven-public") }
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.cognifide.aem") {
                useModule("com.cognifide.gradle:aem-plugin:6.0.0")
            } else if (requested.id.id == "com.neva.fork") {
                useModule("com.neva.gradle:fork-plugin:1.0.7")
            } else if (requested.id.id == "org.jetbrains.kotlin.jvm") {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:1.2.70")
            } else if (requested.id.id == "com.moowork.node") {
                useModule("com.moowork.gradle:gradle-node-plugin:1.2.0")
            }
        }
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
