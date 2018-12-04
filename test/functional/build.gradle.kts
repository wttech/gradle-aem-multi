import com.moowork.gradle.node.yarn.YarnTask
import java.util.function.Consumer

plugins {
    id("java")
    id("com.cognifide.aem.instance")
    id("com.moowork.node")
}

description = "Example - Functional Tests"

tasks{
    register<YarnTask>("testFunctional") {
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

    register("testSetup") {
        description = "Creates env json from gradle properties."
        group = "check"
        doLast {
            val configTemplate = File("${projectDir.absolutePath}/config/_templates/template-jest-config.js").readText()
            val envTemplate = File("${projectDir.absolutePath}/config/_templates/template-puppeteer-environment.js").readText()
            file("${projectDir.absolutePath}/env").mkdirs()
            aem.instances.forEach(Consumer {
                val values = hashMapOf("instanceName" to it.name, "instanceUrl" to it.httpUrl)
                File("${projectDir.absolutePath}/env/${it.name}.config.js")
                        .printWriter().use { out -> out.print(aem.props.expand(configTemplate, values)) }
                File("${projectDir.absolutePath}/env/${it.name}.env.js")
                        .printWriter().use { out -> out.print(aem.props.expand(envTemplate, values)) }
            })
        }
    }

    register<YarnTask>("testInstallNode") {
        setYarnCommand("install")
        group = "check"
        setWorkingDir(file(projectDir.absolutePath))
        outputs.dir(file(projectDir.absolutePath))
    }

    register<YarnTask>("testLint") {
        setYarnCommand("lint")
        group = "check"
        setWorkingDir(file(projectDir.absolutePath))
        outputs.dir(file(projectDir.absolutePath))
    }
}
