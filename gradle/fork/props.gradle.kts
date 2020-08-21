import com.cognifide.gradle.aem.common.instance.local.Source
import com.cognifide.gradle.aem.common.instance.local.OpenMode
import com.neva.gradle.fork.ForkExtension

configure<ForkExtension> {
    properties {
        define("Instance Options", mapOf(
                "instanceType" to {
                    label = "Type"
                    select("local", "remote")
                    description = "Local - instance will be created on local file system\nRemote - connecting to remote instance only"
                    controller { toggle(value == "local", "instanceRunModes", "instanceJvmOpts", "localInstance*") }
                },
                "instanceAuthorHttpUrl" to {
                    label = "Author HTTP URL"
                    url("http://localhost:4502")
                    optional()
                },
                "instanceAuthorEnabled" to {
                    label = "Author Enabled"
                    checkbox(true)
                },
                "instancePublishHttpUrl" to {
                    label = "Publish HTTP URL"
                    url("http://localhost:4503")
                    optional()
                },
                "instancePublishEnabled" to {
                    label = "Publish Enabled"
                    checkbox(true)
                },
                "instanceProvisionEnabled" to {
                    label = "Provision Enabled"
                    description = "Turns on/off automated instance configuration."
                    checkbox(true)
                },
                "instanceProvisionDeployPackageStrict" to {
                    label = "Provision Deploy Package Strict"
                    description = "Check if package is actually deployed on instance.\n" +
                            "By default faster heuristic is used which does not require downloading deployed packages eagerly."
                    checkbox(false)
                }
        ))

        define("Instance Checking", mapOf(
                "instanceCheckHelpEnabled" to {
                    label = "Help"
                    description = "Tries to start bundles automatically when instance is not stable longer time."
                    checkbox(true)
                },
                "instanceCheckBundlesEnabled" to {
                    label = "Bundles"
                    description = "Awaits for all bundles in active state."
                    checkbox(true)
                },
                "instanceCheckInstallerEnabled" to {
                    label = "Installer"
                    description = "Awaits for Sling OSGi Installer not processing any resources."
                    checkbox(true)
                },
                "instanceCheckEventsEnabled" to {
                    label = "Events"
                    description = "Awaits period of time free of OSGi events incoming."
                    checkbox(true)
                },
                "instanceCheckComponentsEnabled" to {
                    label = "Components"
                    description = "Awaits for active platform and application specific components."
                    checkbox(true)
                }
        ))

        define("Local instance", mapOf(
                "localInstanceSource" to {
                    label = "Source"
                    description = "Controls how instances will be created (from scratch, backup or any available source)"
                    select(Source.values().map { it.name.toLowerCase() }, Source.AUTO.name.toLowerCase())
                },
                "localInstanceQuickstartJarUri" to {
                    label = "Quickstart URI"
                    description = "For file named 'cq-quickstart-x.x.x.jar'"
                },
                "localInstanceQuickstartLicenseUri" to {
                    label = "Quickstart License URI"
                    description = "For file named 'license.properties'"
                },
                "localInstanceBackupDownloadUri" to {
                    label = "Backup Download URI"
                    description = "For backup file. Protocols supported: SMB/SFTP/HTTP"
                    optional()
                },
                "localInstanceBackupUploadUri" to {
                    label = "Backup Upload URI"
                    description = "For directory containing backup files. Protocols supported: SMB/SFTP"
                    optional()
                },
                "localInstanceRunModes" to {
                    label = "Run Modes"
                    text("local")
                },
                "localInstanceJvmOpts" to {
                    label = "JVM Options"
                    text("-server -Xmx2048m -XX:MaxPermSize=512M -Djava.awt.headless=true")
                },
                "localInstanceOpenMode" to {
                    label = "Open Automatically"
                    description = "Starts web browser when instances are up."
                    select(OpenMode.values().map { it.name.toLowerCase() }, OpenMode.ALWAYS.name.toLowerCase())
                },
                "localInstanceOpenAuthorPath" to {
                    label = "Open Author Path"
                    text("/aem/start.html")
                },
                "localInstanceOpenPublishPath" to {
                    label = "Open Publish Path"
                    text("/crx/packmgr")
                }
        ))

        define("Package", mapOf(
                "packageDeployAvoidance" to {
                    label = "Deploy Avoidance"
                    description = "Avoids uploading and installing package if identical is already deployed on instance."
                    checkbox(true)
                },
                "packageDamAssetToggle" to {
                    label = "Deploy Without DAM Worklows"
                    description = "Turns on/off temporary disablement of assets processing for package deployment time.\n" +
                            "Useful to avoid redundant rendition generation when package contains renditions synchronized earlier."
                    checkbox(true)
                    dynamic("props")
                },
                "packageValidatorEnabled" to {
                    label = "Validator Enabled"
                    description = "Turns on/off package validation using OakPAL."
                    checkbox(true)
                },
                "packageNestedValidation" to {
                    label = "Nested Validation"
                    description = "Turns on/off separate validation of built subpackages."
                    checkbox(true)
                },
                "packageBundleTest" to {
                    label = "Bundle Test"
                    description = "Turns on/off running tests for built bundles put under install path."
                    checkbox(true)
                }
        ))

        define("Authorization", mapOf(
                "companyUser" to {
                    label = "User"
                    description = "Authorized to access AEM files"
                    defaultValue = System.getProperty("user.name").orEmpty()
                    optional()
                },
                "companyPassword" to {
                    label = "Password"
                    description = "For above user"
                    optional()
                },
                "companyDomain" to {
                    label = "Domain"
                    description = "Needed only when accessing AEM files over SMB"
                    defaultValue = System.getenv("USERDOMAIN").orEmpty()
                    optional()
                }
        ))

        define("Release", mapOf(
                "releaseRepository" to {
                    label = "Repository URL"
                    description = "Nexus, Artifactory etc."
                    defaultValue = "https://nexus.company.com/content/repositories/company-internal"
                    optional()
                },
                "releaseUser" to {
                    label = "User"
                    description = "Authorized to release artifacts to above repository"
                    optional()
                },
                "releasePassword" to {
                    label = "Password"
                    description = "For above user"
                    optional()
                }
        ))

        define("Other", mapOf(
                "notifierEnabled" to {
                    label = "Notifications"
                    description = "Controls displaying of GUI notifications (baloons)"
                    checkbox(true)
                },
                "webpackMode" to {
                    label = "Webpack Mode"
                    description = "Controls optimization of front-end resources (CSS/JS/assets) "
                    select("dev", "prod")
                },
                "testSkip" to {
                    label = "Test Skip"
                    description = "Skip running integration, functional and performance tests."
                    checkbox(false)
                },
                "testBrowser" to {
                    label = "Test Browser"
                    description = "Browser used when running functional tests powered by Cypress"
                    select("auto", "chrome", "chrome:canary", "chromium", "electron", "edge", "edge:canary", "firefox", "firefox:nightly")
                }
        ))
    }
}
