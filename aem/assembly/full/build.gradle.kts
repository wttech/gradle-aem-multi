plugins {
    id("com.cognifide.aem.package")
}

apply(from = rootProject.file("gradle/common.gradle.kts"))

group = "com.company.example.aem"
description = "Example - AEM Full"

aem {
    tasks {
        packageCompose {
            fromProject(":aem:common")
            fromProject(":aem:sites")

            fromProject(":aem:site.demo")
            fromProject(":aem:site.live")

            doLast {
                val opearFile = resolveFile {
                    resolve("com.adobe.acs:acs-aem-commons-oakpal-checks:4.3.4").use {
                        copyToDirectory(it, composedDir)
                    }
                }

                runDocker {
                    operation("Validating CRX package '${composedFile.name}'")
                    volume(composedDir, "/work")
                    image = "adamcin/oakpal:1.5.1"
                    command = "-j -o oakpal-report.json -f ${opearFile.name} ${composedFile.name}"
                    // TODO make working sth below
                    // command = "-p plan.json -j -o oakpal-report.json -f ${opearFile.name} ${composedFile.name}"
                }
            }
        }
    }
}
