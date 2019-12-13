import com.moowork.gradle.node.yarn.YarnTask

plugins {
    id("com.cognifide.aem.common")
    id("com.github.node-gradle.node")
}

apply(from = rootProject.file("gradle/common.gradle.kts"))

description = "Example - Functional Tests"

tasks {
    val args by lazy {
        val baseUrl = aem.prop.string("test.publishUrl") ?: aem.main.environment.hosts.publish.url

        mutableListOf("-c", "baseUrl=$baseUrl").apply {
            if (aem.prop.flag("test.headed")) add("--headed")
            if (aem.prop.flag("test.record")) add("--record")
            aem.prop.string("test.spec")?.let { add("--spec=$it")}
            aem.prop.string("test.browser")?.let { add("--browser=$it")}
        }
    }
    val reportDir = "build/cypress/reports"

    register<YarnTask>("runTests") {
        group = "check"
        description = "Run functional tests (Cypress)"
        dependsOn("yarn")
        mustRunAfter(":aem:environmentAwait")

        setWorkingDir(projectDir)
        setYarnCommand("cypress")
        setArgs(listOf("run") + args)
        doFirst { delete(reportDir) }
    }

    register<YarnTask>("generateReport") {
        group = "check"
        description = "Generate report for functional tests (Cypress)"
        dependsOn("runTests")

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
