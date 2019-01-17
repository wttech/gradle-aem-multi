import com.cognifide.gradle.aem.pkg.tasks.Compose

plugins {
    id("com.cognifide.aem.package")
}

description = "Example - AEM Application Migration"

aem {
    tasks {
        compose {
            vaultProperty("installhook.aecu.class", "de.valtech.aecu.core.installhook.AecuInstallHook")
        }
    }

}
