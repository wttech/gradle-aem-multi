import com.moowork.gradle.node.yarn.YarnTask
import com.cognifide.gradle.aem.pkg.tasks.Compose

plugins {
    id("com.cognifide.aem.package")
    id("com.moowork.node")
}

description = "Example - AEM Application Design"

tasks.register<YarnTask>("webpackPublish") {
    dependsOn("yarn")

    val dir = "src/main/content/jcr_root/etc/designs/example/publish"
    inputs.dir("$dir/src")
    outputs.dir("$dir/dist")
}

tasks.named<Compose>(Compose.NAME) {
    dependsOn(tasks.named("webpackPublish"))
}
