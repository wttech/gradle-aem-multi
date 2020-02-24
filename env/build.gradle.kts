plugins {
    id("com.cognifide.environment")
    id("com.cognifide.aem.instance.local")
}

repositories {
    jcenter()
}

aem {
    instance {
        satisfier {
            packages {
                // "dep.vanity-urls"("pkg/vanityurls-components-1.0.2.zip")
                "dep.core-components-all"("com.adobe.cq:core.wcm.components.all:2.8.0@zip")
                "dep.core-components-examples"("com.adobe.cq:core.wcm.components.examples:2.8.0@zip")
                "tool.ac-tool"("https://repo1.maven.org/maven2/biz/netcentric/cq/tools/accesscontroltool", "accesscontroltool-package/2.3.2/accesscontroltool-package-2.3.2.zip", "accesscontroltool-oakindex-package/2.3.2/accesscontroltool-oakindex-package-2.3.2.zip")
                "tool.aem-easy-content-upgrade"("https://github.com/valtech/aem-easy-content-upgrade/releases/download/2.0.0/aecu.bundle-2.0.0.zip")
                "tool.search-webconsole-plugin"("com.neva.felix:search-webconsole-plugin:1.2.0")
            }
        }
        provisioner {
            // https://github.com/Cognifide/gradle-aem-plugin/blob/master/docs/instance-plugin.md#task-instanceprovision
        }
    }

    localInstance {
        install {
            files {
                // https://github.com/Cognifide/gradle-aem-plugin/blob/master/docs/local-instance-plugin.md#pre-installed-osgi-bundles-and-crx-packages
            }
        }
    }

    tasks {
        environmentUp {
            mustRunAfter(instanceUp, instanceSatisfy, instanceProvision, instanceSetup)
            mustRunAfter(":app:aem:migration:packageDeploy") // last step of task ':develop'
        }

        environmentAwait {
            mustRunAfter(instanceAwait)
        }
    }
}

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
                    execShell("Starting HTTPD server", "/usr/sbin/httpd -k start")
                }
                reload {
                    cleanDir("/opt/aem/dispatcher/cache/content/example/demo", "/opt/aem/dispatcher/cache/content/example/live")
                    execShell("Restarting HTTPD server", "/usr/sbin/httpd -k restart")
                }
                dev {
                    watchConfigDir("conf")
                }
            }
        }
    }
    hosts {
        "http://author.example.com" { tag("author") }
        "http://example.com" { tag("publish") }
        "http://dispatcher.example.com" { tag("dispatcher") }
    }

    healthChecks {
        url("Live site", "http://example.com/en-us.html") { containsText("English US") }
        url("Author module 'Sites'", "http://author.example.com/sites.html") {
            options { basicCredentials = aem.authorInstance.credentials }
            containsText("Sites")
        }
    }
}
