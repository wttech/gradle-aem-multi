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
                    volume(composedDir, "/work")
                    image = "adamcin/oakpal"
                    command = "--file ${opearFile.name} ${composedFile.name}"
                }
            }
        }
    }
}
