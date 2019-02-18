plugins {
    id("com.cognifide.aem.environment")
}

aem {
    tasks {
        setup {
            dependsOn(":deploy")
        }

        satisfy {
            packages {
                group("dep.vanity-urls") { /* local("pkg/vanityurls-components-1.0.2.zip") */ }
                group("dep.kotlin") { dependency("org.jetbrains.kotlin:kotlin-osgi-bundle:1.3.20") }
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

        environment {
            healthChecks {
                "http://example.com/en-us.html" respondsWith {
                    status = 200
                    text = "English"
                }
                "http://demo.example.com/en-us.html" respondsWith {
                    status = 200
                    text = "English"
                }
                "http://author.example.com/libs/granite/core/content/login.html?resource=%2F&\$\$login\$\$=%24%24login%24%24&j_reason=unknown&j_reason_code=unknown" respondsWith {
                    status = 200
                    text = "AEM Sign In"
                }
            }
        }
    }
}

apply(from = "gradle/common.gradle.kts")
