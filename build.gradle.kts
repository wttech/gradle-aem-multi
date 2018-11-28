import com.cognifide.gradle.aem.instance.tasks.Satisfy
import com.cognifide.gradle.aem.instance.tasks.Setup
import com.neva.gradle.fork.ForkTask

plugins {
    id("com.neva.fork")
    id("com.cognifide.aem.instance")
    id("com.moowork.node") apply false
    id("org.jetbrains.kotlin.jvm") apply false
}

description = "Example"
defaultTasks = listOf(":deploy")

aem {
    tasks {
//        sequence("deploy") { // TODO setupSequence
//            dependsOn(
//                path(":aemSatisfy"),
//                path(":aem.full:aemDeploy"),
//                await("full"),
//                path(":aem.migration:aemDeploy"),
//                await("migration"),
//                path(":test.integration:test"),
//                path(":test.functional:test")
//            )
//        }
    }
}

tasks.named<Satisfy>(Satisfy.NAME).configure {
    packages {
        group("dep.vanity-urls") { /* local("pkg/vanityurls-components-1.0.2.zip") */ }
        group("dep.kotlin") { dependency("org.jetbrains.kotlin:kotlin-osgi-bundle:1.3.0") }
        group("dep.acs-aem-commons") { url("https://github.com/Adobe-Consulting-Services/acs-aem-commons/releases/download/acs-aem-commons-3.17.0/acs-aem-commons-content-3.17.0-min.zip") }
        group("tool.aem-easy-content-upgrade") { url("https://github.com/valtech/aem-easy-content-upgrade/releases/download/1.4.0/aecu.bundle-1.4.0.zip") }
        group("tool.search-webconsole-plugin") { dependency("com.neva.felix:search-webconsole-plugin:1.2.0") }
    }
}

tasks.named<ForkTask>(ForkTask.NAME).configure {
    config {
        cloneFiles()
        moveFiles(mapOf(
                "/com/company/aem/example" to "/{{projectGroup|substitute('.', '/')}}/{{projectName}}",
                "/example" to "/{{projectName}}"
        ))
        replaceContents(mapOf(
                "com.company.aem.example" to "{{projectGroup}}.{{projectName}}",
                "com.company.aem" to "{{projectGroup}}",
                "Example" to "{{projectLabel}}",
                "example" to "{{projectName}}"
        ))
    }
}

apply(from = "gradle/common.gradle.kts")
