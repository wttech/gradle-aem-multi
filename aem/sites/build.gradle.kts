import com.moowork.gradle.node.yarn.YarnTask

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.cognifide.aem.bundle")
    id("com.moowork.node")
}

description = "Example - AEM Sites"

dependencies {
    implementation(project(":aem:common"))
}

aem {
    tasks {
        register<YarnTask>("webpackPublish") {
            dependsOn("yarn")

            val dir = "src/main/content/jcr_root/etc/designs/example/sites/publish"
            inputs.dir("$dir/src")
            outputs.dir("$dir/dist")
        }

        compose {
            dependsOn(named("webpackPublish"))
        }
    }
}
