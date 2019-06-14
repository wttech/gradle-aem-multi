import com.cognifide.gradle.aem.AemExtension

configure<AemExtension> {
    environment {
        hosts(
                "example.com",
                "demo.example.com",
                "author.example.com",
                "dispatcher.example.com",
                "knotx.example.com"
        )
        distributions {
            download("http://download.macromedia.com/dispatcher/download/dispatcher-apache2.4-linux-x86_64-4.3.2.tar.gz").then {
                copyArchiveFile(it, "**/dispatcher-apache*.so", distributionFile("mod_dispatcher.so"))
            }
        }
        directories {
            regular(
                    "httpd/logs",
                    "knotx/logs"
            )
            cache(
                    "httpd/cache/content/example/live",
                    "httpd/cache/content/example/demo"
            )
        }
        healthChecks {
            url("Live site", "http://example.com/en-us.html", text = "English")
            url("Demo site", "http://demo.example.com/en-us.html", text = "English")
            url("Author login", "http://author.example.com/libs/granite/core/content/login.html" +
                    "?resource=%2F&\$\$login\$\$=%24%24login%24%24&j_reason=unknown&j_reason_code=unknown", text = "AEM Sign In")
            url("Knot.x", "http://knotx.example.com/", statusCode = 404)
        }
    }

    tasks {
        registerOrConfigure("setup", "resetup") {
            dependsOn(":develop")
        }

        instanceSatisfy {
            packages {
                group("dep.vanity-urls") { /* local("pkg/vanityurls-components-1.0.2.zip") */ }
                group("dep.kotlin") { resolve("org.jetbrains.kotlin:kotlin-osgi-bundle:${Build.KOTLIN_VERSION}") }
                group("dep.acs-aem-commons") { download("https://github.com/Adobe-Consulting-Services/acs-aem-commons/releases/download/acs-aem-commons-4.0.0/acs-aem-commons-content-4.0.0-min.zip") }
                group("tool.aem-easy-content-upgrade") { download("https://github.com/valtech/aem-easy-content-upgrade/releases/download/1.4.0/aecu.bundle-1.4.0.zip") }
                group("tool.search-webconsole-plugin") { resolve("com.neva.felix:search-webconsole-plugin:1.2.0") }
            }
        }

        // Here is a desired place for defining custom AEM tasks
        // https://github.com/Cognifide/gradle-aem-plugin#implement-custom-aem-tasks

        register("doSomething") {
            description = "Does something"
            doLast {
                aem.sync {
                    // use instance services: 'http', 'repository', 'packageManager', 'osgiFramework', 'groovyConsole'
                }
            }
        }

    }
}
