import com.moowork.gradle.node.yarn.YarnTask

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.cognifide.aem.bundle")
    id("com.github.node-gradle.node")
}

apply(from = rootProject.file("gradle/common.gradle.kts"))

group = "com.company.example.aem"
description = "Example - AEM Sites"

dependencies {
    implementation(project(":aem:common"))
}

aem {
    val publishDir = "${packageOptions.jcrRootDir}/apps/example/sites/clientlibs/page/publish"

    tasks {
        register<YarnTask>("webpackPublish") {
            description = "Builds sites publish clientlib using Webpack"
            dependsOn("yarn")
            setYarnCommand("buildPublish")


            inputs.file("package.json")
            inputs.dir("$publishDir/src")
            outputs.dir("$publishDir/dist")
        }

        named<Task>("clean") {
            doLast {
                delete("$publishDir/dist")
            }
        }

        packageCompose {
            dependsOn(named("webpackPublish"))
            vaultDefinition {
                property("installhook.actool.class", "biz.netcentric.cq.tools.actool.installhook.AcToolInstallHook")
            }
        }
    }
}
