plugins {
    id("com.cognifide.aem.package")
}

apply(from = rootProject.file("app/common.gradle.kts"))
apply(from = rootProject.file("app/aem/common.gradle.kts"))

description = "Example - AEM Migration"

aem {
    tasks {
        packageCompose {
            vaultDefinition {
                property("installhook.aecu.class", "de.valtech.aecu.core.installhook.AecuInstallHook")
            }
        }
    }
}
