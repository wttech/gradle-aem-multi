plugins {
    id("com.cognifide.htl")
    id("com.cognifide.aem.package")
    id("com.cognifide.aem.package.sync")
}

apply(from = rootProject.file("app/common.gradle.kts"))
apply(from = rootProject.file("app/aem/common.gradle.kts"))

description = "Example - UI Apps"

aem {
    tasks {
        packageCompose {
            dependsOn(":app:aem:ui.frontend:webpack")
            installBundleProject(":app:aem:core")
            vaultDefinition {
                property("installhook.actool.class", "biz.netcentric.cq.tools.actool.installhook.AcToolInstallHook")
            }
        }
    }
}
