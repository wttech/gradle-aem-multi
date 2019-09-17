import com.moowork.gradle.node.yarn.YarnTask

plugins {
    id("com.cognifide.aem.common")
    id("com.moowork.node")
}

description = "Example - Performance Tests"

aem {
    tasks {
        register<YarnTask>("run") {
            dependsOn("yarn")
            group = "check"

            val baseUrl = props.string("test.baseUrl") ?: main.environment.hosts.publish.url
            val configName = "${baseUrl.substringAfter("://")}.conf"
            val configTpl = file("sites/$configName")
                    .takeIf { it.exists() } ?: file("sites/default.conf")
            val configFile = file("build/sites/$configName")
            val reportsDir = file("build/reports")

            setWorkingDir(projectDir)
            setYarnCommand("lighthouse-batch")
            setArgs(listOf("--file", "$configFile", "--html", "--out", "$reportsDir"))

            doFirst {
                configFile.parentFile.mkdirs()
                configFile.printWriter().use {writer ->
                    configTpl.forEachLine { path ->
                        writer.println("$baseUrl$path")
                    }
                }

            }
        }
    }
}
