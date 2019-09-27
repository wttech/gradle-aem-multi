import com.moowork.gradle.node.yarn.YarnTask

plugins {
    id("com.cognifide.aem.common")
    id("com.moowork.node")
}

apply(from = rootProject.file("gradle/common.gradle.kts"))

description = "Example - Functional Tests"

aem {
    tasks {
        val args by lazy {
            val baseUrl = props.string("test.publishUrl") ?: main.environment.hosts.publish.url

            mutableListOf("-c", "baseUrl=$baseUrl").apply {
                if (props.flag("test.headed")) add("--headed")
                if (props.flag("test.record")) add("--record")
                props.string("test.spec")?.let { add("--spec=$it")}
                props.string("test.browser")?.let { add("--browser=$it")}
            }
        }
        val reportDir = "build/cypress/reports"

        register<YarnTask>("run") {
            group = "check"
            description = "Run functional tests (Cypress)"
            dependsOn("yarn")
            finalizedBy("generateReport")

            setWorkingDir(projectDir)
            setYarnCommand("cypress")
            setArgs(listOf("run") + args)
            doFirst { delete(reportDir) }
        }

        register<YarnTask>("generateReport") {
            group = "check"
            description = "Generate report for functional tests (Cypress) "

            setWorkingDir(projectDir)
            setYarnCommand("node")
            setArgs(listOf("scripts/generateReport.js"))
        }

        register<YarnTask>("openGui") {
            group = "check"
            description = "Open functional tests GUI runner (Cypress)"
            dependsOn("yarn")

            setWorkingDir(projectDir)
            setYarnCommand("cypress")
            setArgs(listOf("open") + args)
            doFirst { delete(reportDir) }
        }
    }
}
