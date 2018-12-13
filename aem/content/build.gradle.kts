import com.cognifide.gradle.aem.pkg.tasks.Compose

plugins {
    id("com.cognifide.aem.package")
}

description = "Example - AEM Content"

tasks {
    named<Compose>(Compose.NAME) {
        fromProject(":aem:content.demo")
        fromProject(":aem:content.init")
    }
}
