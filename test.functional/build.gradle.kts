import com.moowork.gradle.node.yarn.YarnTask
import java.util.function.Consumer

plugins {
    id("java")
    id("com.cognifide.aem.instance")
    id("com.moowork.node")
}

description = "Example - Functional Tests"

tasks.create<YarnTask>("testFunctional") {
    dependsOn("testSetup", "testInstallNode")
    group = "check"
    setYarnCommand("jest")
    setWorkingDir(file("${projectDir.absolutePath}/test.functional"))
    outputs.dir(file("${projectDir.absolutePath}/test.functional"))
    doFirst {
        val props = mutableListOf()
        if (project.hasProperty("aem.env")) {
            props.addAll(listOf("--config", "./env/${project.properties["aem.env"]}.config.js"))
        } else {
            props.addAll(listOf("--config", "./env/local-publish.config.js"))
        }
        args = props
    }
}

tasks.create("testSetup") {
    description = "Creates env json from gradle properties."
    group = "check"
    doLast {
        val configTemplate = File("test.functional/config/_templates/template-jest-config.js").readText()
        val envTemplate = File("test.functional/config/_templates/template-puppeteer-environment.js").readText()
        file("test.functional/env").mkdirs()
        aem.instances.forEach(Consumer {
            val values = hashMapOf("instance-name" to it.name, "instance-url" to it.httpUrl)
            File("test.functional/env/${it.name}.config.js")
                    .printWriter().use { out -> out.print(aem.props.expand(configTemplate, values)) }
            File("test.functional/env/${it.name}.env.js")
                    .printWriter().use { out -> out.print(aem.props.expand(envTemplate, values)) }
        })
    }
}

tasks.create<YarnTask>("testInstallNode") {
    setYarnCommand("install")
    group = "check"
    setWorkingDir(file("${projectDir.absolutePath}/test.functional"))
    outputs.dir(file("${projectDir.absolutePath}/test.functional"))
}

tasks.create<YarnTask>("testLint") {
    setYarnCommand("lint")
    group = "check"
    setWorkingDir(file("${projectDir.absolutePath}/test.functional"))
    outputs.dir(file("${projectDir.absolutePath}/test.functional"))
}

apply(from = "../gradle/test.gradle.kts")
