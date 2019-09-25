import com.moowork.gradle.node.yarn.YarnTask

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.cognifide.aem.bundle")
    id("com.moowork.node")
}

apply(from = rootProject.file("gradle/common.gradle.kts"))

group = "com.company.example.aem"
description = "Example - AEM Sites"

dependencies {
    implementation(project(":aem:common"))
}

aem {
    tasks {
        register<YarnTask>("webpackPublish") {
            description = "Builds sites publish clientlib using Webpack"
            dependsOn("yarn")
            setYarnCommand("buildPublish")

            val dir = "${packageOptions.jcrRootDir}/apps/example/sites/clientlibs/page/publish"

            inputs.file("package.json")
            inputs.dir("$dir/src")
            outputs.dir("$dir/dist")
        }

        packageCompose {
            dependsOn(named("webpackPublish"))
        }
    }
}
