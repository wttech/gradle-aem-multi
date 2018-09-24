import com.moowork.gradle.node.NodeExtension
import com.moowork.gradle.node.yarn.YarnTask

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

tasks.named<Task>("aemCompose") {
    dependsOn("webpackPublish")
}

configure<NodeExtension> {
    version = "6.11.1"
    yarnVersion = "0.27.5"
    download = true
}
