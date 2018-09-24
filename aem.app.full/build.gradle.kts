import com.cognifide.gradle.aem.pkg.ComposeTask

plugins {
    id("com.cognifide.aem.package")
}

description = "Example - AEM Application Full"

tasks.named<ComposeTask>("aemCompose") {
    includeProject(":aem.app.common")
    includeProject(":aem.app.core")
    includeProject(":aem.app.author", "author")
    includeProject(":aem.app.publish", "publish")
    includeProject(":aem.app.config")
    includeProject(":aem.app.design")
}
