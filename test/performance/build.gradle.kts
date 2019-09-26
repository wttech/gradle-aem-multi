import com.moowork.gradle.node.yarn.YarnExecRunner

plugins {
    id("com.cognifide.aem.common")
    id("com.moowork.node")
}

apply(from = rootProject.file("gradle/common.gradle.kts"))

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
                val lighthouseConfig = file("lighthouse/$configName/lighthouse.json")
                        .takeIf { it.exists() } ?: file("lighthouse/default/config.json")
                val reportDir = file("build/lighthouse")
                val cliConfig = (file("lighthouse/$configName/cli.json")
                        .takeIf { it.exists() } ?: file("lighthouse/default/cli.json")).run {
                    formats.asJson(readText())
                }
                val cliPaths: List<String> = cliConfig.read("paths")
                val cliArgs: List<String> = cliConfig.read("args")

                reportDir.mkdirs()
                cliPaths.forEach { path ->
                    if (path.isNotBlank()) {
                        val url = "$baseUrl$path"
                        val fileName = url.substringAfter("://").replace("/", "_")

                        YarnExecRunner(project).apply {
                            workingDir = projectDir
                            arguments = mutableListOf(
                                    "lighthouse-ci", url, "--report=$reportDir",
                                    "--filename=$fileName", "--config-path=$lighthouseConfig"
                            ) + cliArgs
                            execute()
                        }
                    }
                }
            }
        }
    }
}
