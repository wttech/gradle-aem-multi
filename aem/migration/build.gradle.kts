plugins {
    id("com.cognifide.aem.package")
}

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
