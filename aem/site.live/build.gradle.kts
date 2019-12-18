plugins {
    id("com.cognifide.aem.package")
    id("com.cognifide.aem.package.sync")
}

apply(from = rootProject.file("gradle/common.gradle.kts"))

group = "com.company.example.aem"
description = "Example - Site Live"
