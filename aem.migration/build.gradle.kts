import com.cognifide.gradle.aem.api.AemExtension
import com.cognifide.gradle.aem.pkg.ComposeTask

plugins {
    id("com.cognifide.aem.package")
}

description = "Example - AEM Application Migration"

tasks.named<ComposeTask>("aemCompose").configure {
    vaultProperty("installhook.aecu.class", "de.valtech.aecu.core.installhook.AecuInstallHook")
}
