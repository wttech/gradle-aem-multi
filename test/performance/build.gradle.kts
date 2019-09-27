import com.cognifide.gradle.aem.common.utils.Patterns
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
                val suiteName = props.string("test.lighthouseSuite")
                val suiteConfig = file("lighthouse/suites.json").run {
                    formats.fromJson(readText(), LighthouseConfig::class.java)
                }
                val baseUrl = props.string("test.publishUrl") ?: main.environment.hosts.publish.url
                val reportDir = file("build/lighthouse")

                reportDir.mkdirs()

                when {
                    !suiteName.isNullOrBlank() -> suiteConfig.suites.filter { it.name == suiteName }
                    else -> suiteConfig.suites.filter { Patterns.wildcard(baseUrl, it.baseUrls) }
                }.ifEmpty {
                    suiteConfig.suites.filter { it.name == "default" }
                }.forEach { suite ->
                    logger.info("Running Lighthouse Suite '${suite.name}'")
                    suite.paths.forEach { path ->
                        val url = "$baseUrl$path"
                        val fileName = url.substringAfter("://").replace("/", "_")

                        YarnExecRunner(project).apply {
                            workingDir = projectDir
                            arguments = mutableListOf("lighthouse-ci", url, "--report=$reportDir",
                                    "--filename=$fileName"
                            ) + suite.args
                            execute()
                        }
                    }
                }
            }
        }
    }
}
