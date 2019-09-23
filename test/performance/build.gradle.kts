import com.moowork.gradle.node.yarn.YarnExecRunner

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

            val baseUrl = props.string("test.baseUrl") ?: main.environment.hosts.publish.url
            val configName = "${baseUrl.substringAfter("://")}.conf"
            val configFile = file("sites/$configName")
                    .takeIf { it.exists() } ?: file("sites/default.conf")
            val reportDir = file("build/lighthouse")

            doLast {
                reportDir.mkdirs()
                configFile.forEachLine { path ->
                    if (path.isNotBlank()) {
                        val url = "$baseUrl$path"
                        val fileName = url.substringAfter("://").replace("/", "_")

                        YarnExecRunner(project).apply {
                            workingDir = projectDir
                            arguments = mutableListOf(
                                    "lighthouse-ci", url, "--report=$reportDir",
                                    "--filename=$fileName", "--config-path=lighthouse.json"
                            )
                            execute()
                        }
                    }
                }
            }
        }
    }
}
