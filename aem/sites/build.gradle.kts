plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.cognifide.aem.bundle")
    id("com.cognifide.aem.package.sync")
}

apply(from = rootProject.file("gradle/common.gradle.kts"))

group = "com.company.example.aem"
description = "Example - AEM Sites"

dependencies {
    implementation(project(":aem:common"))
}

aem {
    tasks {
        packageCompose {
            vaultDefinition {
                property("installhook.actool.class", "biz.netcentric.cq.tools.actool.installhook.AcToolInstallHook")
            }
        }
    }
}
