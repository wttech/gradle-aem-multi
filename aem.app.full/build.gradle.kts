import com.cognifide.gradle.aem.pkg.ComposeTask

plugins {
    id("com.cognifide.aem.package")
}

description = "Example - AEM Application Full"

tasks.named<ComposeTask>("aemCompose") {
    fromProject(":aem.app.common")
    fromProject(":aem.app.core")
    fromProject(":aem.app.author"/*, "author"*/)
    fromProject(":aem.app.publish"/*, "publish"*/)
    fromProject(":aem.app.config")
    fromProject(":aem.app.design")
}
