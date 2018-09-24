import com.cognifide.gradle.aem.api.AemExtension

plugins {
    id("com.cognifide.aem.package")
}

description = "Example - AEM Application Migration"
configure<AemExtension> {
    config {
        packageEntries = mutableMapOf(
            "installhook.aecu.class" to "de.valtech.aecu.core.installhook.AecuInstallHook"
        )
    }
}
