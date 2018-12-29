import com.cognifide.gradle.aem.instance.tasks.Satisfy
import com.cognifide.gradle.aem.instance.tasks.Setup

plugins {
    id("com.cognifide.aem.instance")
}

aem {
    tasks {
        setup {
            dependsOn(":deploy")
        }

        satisfy {
            packages {
                group("dep.vanity-urls") { /* local("pkg/vanityurls-components-1.0.2.zip") */ }
                group("dep.kotlin") { dependency("org.jetbrains.kotlin:kotlin-osgi-bundle:1.3.10") }
                group("dep.acs-aem-commons") { url("https://github.com/Adobe-Consulting-Services/acs-aem-commons/releases/download/acs-aem-commons-3.17.0/acs-aem-commons-content-3.17.0-min.zip") }
                group("tool.aem-easy-content-upgrade") { url("https://github.com/valtech/aem-easy-content-upgrade/releases/download/1.4.0/aecu.bundle-1.4.0.zip") }
                group("tool.search-webconsole-plugin") { dependency("com.neva.felix:search-webconsole-plugin:1.2.0") }
            }
        }

        // here is a desired place for defining custom AEM tasks

        register("aemConfigure") {
            doLast {
                aem.sync(aem.publishInstances) {
                    disableComponent("org.apache.sling.jcr.davex.impl.servlets.SlingDavExServlet")
                }
            }
        }
    }
}

apply(from = "gradle/common.gradle.kts")
