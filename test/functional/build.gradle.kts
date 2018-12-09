import com.moowork.gradle.node.yarn.YarnTask
import java.util.function.Consumer

plugins {
    id("java")
    id("com.cognifide.aem.base")
    id("com.moowork.node")
}

description = "Example - Functional Tests"

tasks {

    named("check") {
        dependsOn(named("runJestPuppeteer"))
    }

    register<YarnTask>("runJestPuppeteer") {
        dependsOn("setupJestPuppeteer")
        group = "check"
        setYarnCommand("jest")
        setWorkingDir(file(file("test.functional")))
        outputs.dir(file(file("test.functional")))
        doFirst {
            val configTemplate = project.file("config/_templates/jest-config.js").readText()
            val envTemplate = project.file("config/_templates/puppeteer-environment.js").readText()
            val root = file("env")

            root.deleteRecursively()
            root.mkdirs()

            aem.instances.forEach {
                val values = mapOf("instance" to it)
                File(root, "${it.name}.config.js").printWriter().use { it.print(aem.props.expand(configTemplate, values)) }
                File(root, "${it.name}.env.js").printWriter().use { it.print(aem.props.expand(envTemplate, values)) }
            }

            args = listOf("--config", project.file("env/${project.findProject("aem.env") ?: "local-publish"}.config.js").toString())
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
