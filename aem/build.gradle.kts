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
                    resolve {
                        resolveFiles {
                            download("http://download.macromedia.com/dispatcher/download/dispatcher-apache2.4-linux-x86_64-4.3.3.tar.gz").use {
                                copyArchiveFile(it, "**/dispatcher-apache*.so", file("modules/mod_dispatcher.so"))
                            }
                        }
                        ensureDir("cache", "logs")
                    }
                    up {
                        ensureDir("/usr/local/apache2/logs", "/opt/aem/dispatcher/cache/content/example/demo", "/opt/aem/dispatcher/cache/content/example/live")
                        execShell("Starting HTTPD server", "/usr/local/apache2/bin/httpd -k start")
                    }
                    reload {
                        cleanDir("/opt/aem/dispatcher/cache/content/example/demo", "/opt/aem/dispatcher/cache/content/example/live")
                        execShell("Restarting HTTPD server", "/usr/local/apache2/bin/httpd -k restart")
                    }
                    dev {
                        watchConfigDir("conf")
                    }
                }
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

        environmentUp {
            mustRunAfter(":aem:migration:packageDeploy") // last step of ':develop'
        }

        instanceSatisfy {
            packages {
                // "dep.vanity-urls"("pkg/vanityurls-components-1.0.2.zip")
                "dep.kotlin"("org.jetbrains.kotlin:kotlin-osgi-bundle:${Build.KOTLIN_VERSION}")
                "dep.core-components-all"("com.adobe.cq:core.wcm.components.all:2.8.0@zip")
                // "dep.core-components-examples"("com.adobe.cq:core.wcm.components.examples:2.8.0@zip")
                "tool.ac-tool"("https://repo1.maven.org/maven2/biz/netcentric/cq/tools/accesscontroltool", "accesscontroltool-package/2.3.2/accesscontroltool-package-2.3.2.zip", "accesscontroltool-oakindex-package/2.3.2/accesscontroltool-oakindex-package-2.3.2.zip")
                "tool.aem-easy-content-upgrade"("https://github.com/valtech/aem-easy-content-upgrade/releases/download/2.0.0/aecu.bundle-2.0.0.zip")
                "tool.search-webconsole-plugin"("com.neva.felix:search-webconsole-plugin:1.2.0")
            }
        }

        instanceProvision {
            // https://github.com/Cognifide/gradle-aem-plugin#task-instanceprovision
        }
    }
}
