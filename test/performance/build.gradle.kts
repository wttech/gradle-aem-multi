import com.moowork.gradle.node.yarn.YarnExecRunner

class CliConfig {
    var paths = listOf<String>()
    var args = listOf<String>()
}

plugins {
    id("com.cognifide.aem.common")
    id("com.moowork.node")
}

description = "Example - Performance Tests"

aem {
    tasks {
        register("run") {
            dependsOn("yarn")
            group = "check"
            description = "Run performance tests (Lighthouse)"

            doLast {
                val baseUrl = props.string("test.baseUrl") ?: main.environment.hosts.publish.url
                val configName = baseUrl.substringAfter("://")

                val cliConfig = (file("sites/$configName/cli.json")
                        .takeIf { it.exists() } ?: file("sites/default/cli.json")).run {
                    formats.fromJson(readText(), CliConfig::class.java)
                }
                val lighthouseConfig = file("sites/$configName/lighthouse.json")
                        .takeIf { it.exists() } ?: file("sites/default/lighthouse.json")
                val reportDir = file("build/lighthouse")

                reportDir.mkdirs()
                cliConfig.paths.forEach { path ->
                    if (path.isNotBlank()) {
                        val url = "$baseUrl$path"
                        val fileName = url.substringAfter("://").replace("/", "_")

                        YarnExecRunner(project).apply {
                            workingDir = projectDir
                            arguments = mutableListOf(
                                    "lighthouse-ci", url, "--report=$reportDir",
                                    "--filename=$fileName", "--config-path=$lighthouseConfig"
                            ) + cliConfig.args
                            execute()
                        }
                    }
                }
            }
        }
    }
}
