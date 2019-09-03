import com.moowork.gradle.node.yarn.YarnTask
import java.util.function.Consumer

plugins {
    id("com.cognifide.aem.common")
    id("com.moowork.node")
}

description = "Example - Functional Tests"

tasks {

    register<YarnTask>("test") {
        dependsOn("setupTest")
        group = "check"

        setYarnCommand("jest")
        setWorkingDir(projectDir)

        doFirst {
            val configTemplate = project.file("config/_templates/jest-config.js").readText()
            val envTemplate = project.file("config/_templates/puppeteer-environment.js").readText()
            val root = file("env")

            root.deleteRecursively()
            root.mkdirs()

            val instance = aem.namedInstance(defaultName = "local-publish")
            val templateVars = mapOf("instance" to instance)

            File(root, "${instance.name}.config.js").printWriter().use { it.print(aem.props.expand(configTemplate, templateVars)) }
            File(root, "${instance.name}.env.js").printWriter().use { it.print(aem.props.expand(envTemplate, templateVars)) }

            args = listOf("--config", project.file("env/${instance.name}.config.js").toString())
        }
    }

    register<YarnTask>("setupTest") {
        group = "check"

        setYarnCommand("install")
        setWorkingDir(projectDir)

        outputs.dir(projectDir)
    }

    register<YarnTask>("runLint") {
        group = "check"

        setYarnCommand("lint")
        setWorkingDir(projectDir)

        outputs.dir(projectDir)
    }
}
