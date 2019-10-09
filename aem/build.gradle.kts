import com.cognifide.gradle.aem.AemExtension

plugins {
    id("com.cognifide.aem.instance")
    id("com.cognifide.aem.environment")
}

apply(from = rootProject.file("gradle/common.gradle.kts"))

aem {
    environment {
        docker {
            containers {
                "httpd" {
                    reload {
                        cleanDir(
                                "/opt/aem/dispatcher/cache/content/example/live",
                                "/opt/aem/dispatcher/cache/content/example/demo"
                        )
                        ensureDir("/usr/local/apache2/logs")
                    }
                }
            }
        }
        distributions {
            download("http://download.macromedia.com/dispatcher/download/dispatcher-apache2.4-linux-x86_64-4.3.2.tar.gz").then {
                copyArchiveFile(it, "**/dispatcher-apache*.so", distributionFile("mod_dispatcher.so"))
            }
        }
        hosts {
            author("http://author.example.com")
            publish("http://demo.example.com") { tag("test") }
            publish("http://example.com") { tag("live") }
            other("http://dispatcher.example.com")
        }
        healthChecks {
            url("Live site", "http://example.com/en-us.html") { containsText("English US") }
            url("Demo site", "http://demo.example.com/en-us.html") { containsText("English US") }
            url("Author module 'Sites'", "http://author.example.com/sites.html") {
                options { basicCredentials = authorInstance.credentials }
                containsText("Sites")
            }
        }
    }

    localInstance {
        install {
            files {
                // https://github.com/Cognifide/gradle-aem-plugin#pre-installed-osgi-bundles-and-crx-packages
            }
        }
    }

    tasks {
        registerOrConfigure<Task>("setup", "resetup") {
            dependsOn(":develop")
        }

        instanceSatisfy {
            packages {
                group("dep.vanity-urls") { /* useLocal("pkg/vanityurls-components-1.0.2.zip") */ }
                group("dep.kotlin") { resolve("org.jetbrains.kotlin:kotlin-osgi-bundle:${Build.KOTLIN_VERSION}") }
                group("dep.acs-aem-commons") { download("https://github.com/Adobe-Consulting-Services/acs-aem-commons/releases/download/acs-aem-commons-4.0.0/acs-aem-commons-content-4.0.0-min.zip") }
                group("tool.ac-tool") { download("https://repo1.maven.org/maven2/biz/netcentric/cq/tools/accesscontroltool", "accesscontroltool-package/2.3.2/accesscontroltool-package-2.3.2.zip", "accesscontroltool-oakindex-package/2.3.2/accesscontroltool-oakindex-package-2.3.2.zip") }
                group("tool.aem-easy-content-upgrade") { download("https://github.com/valtech/aem-easy-content-upgrade/releases/download/2.0.0/aecu.bundle-2.0.0.zip") }
                group("tool.search-webconsole-plugin") { resolve("com.neva.felix:search-webconsole-plugin:1.2.0") }
            }
        }

        instanceProvision {
            // https://github.com/Cognifide/gradle-aem-plugin#task-instanceprovision
        }

        // Here is a desired place for defining custom AEM tasks
        // https://github.com/Cognifide/gradle-aem-plugin#implement-custom-aem-tasks

        /*
        register("doSomething") {
            description = "Does something"
            doLast {
                aem.sync {
                    // use instance services: http, repository, packageManager, osgiFramework, groovyConsole
                }
            }
        }
        */
    }
}
