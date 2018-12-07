import com.moowork.gradle.node.yarn.YarnTask
import java.util.function.Consumer

plugins {
    id("java")
    id("com.cognifide.aem.base")
    id("com.moowork.node")
}

description = "Example - Functional Tests"

tasks {

    named("check") { dependsOn(named("runJestPuppeteer")) }

    register<YarnTask>("runJestPuppeteer") {
        dependsOn("setupJestPuppeteer")
        group = "check"
        setYarnCommand("jest")
        setWorkingDir(file(file("test.functional")))
        outputs.dir(file(file("test.functional")))
        doFirst {
            val configTemplate = project.file("config/_templates/template-jest-config.js").readText()
            val envTemplate = project.file("config/_templates/template-puppeteer-environment.js").readText()
            file("${projectDir.absolutePath}/env").mkdirs()
            aem.instances.forEach {
                val values = hashMapOf("instance.name" to it.name, "instance.json" to it.json)
                project.file("/env/${it.name}.config.js")
                        .printWriter().use { out -> out.print(aem.props.expand(configTemplate, values)) }
                project.file("/env/${it.name}.env.js")
                        .printWriter().use { out -> out.print(aem.props.expand(envTemplate, values)) }
            }

            args = listOf("--config", "./env/${project.findProject("aem.env")
                    ?: "local-publish"}.config.js")
        }
    }

    register<YarnTask>("setupJestPuppeteer") {
        setYarnCommand("install")
        group = "check"
        setWorkingDir(file(projectDir.absolutePath))
        outputs.dir(file(projectDir.absolutePath))
    }

    register<YarnTask>("runLint") {
        setYarnCommand("lint")
        group = "check"
        setWorkingDir(file(projectDir.absolutePath))
        outputs.dir(file(projectDir.absolutePath))
    }
}
