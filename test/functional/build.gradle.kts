import com.moowork.gradle.node.yarn.YarnTask

plugins {
    id("com.cognifide.aem.common")
    id("com.moowork.node")
}

description = "Example - Functional Tests"

aem {
    tasks {
        register<YarnTask>("run") {
            dependsOn("yarn")
            group = "check"
            description = "Run functional tests (Cypress)"

            val baseUrl = props.string("test.baseUrl") ?: main.environment.hosts.publish.url

            setWorkingDir(projectDir)
            setYarnCommand("cypress")
            setArgs(mutableListOf("run", "-c", "baseUrl=$baseUrl").apply {
                if (props.flag("test.headed")) add("--headed")
                if (props.flag("test.record")) add("--record")
                props.string("test.spec")?.let { add("--spec=$it")}
                props.string("test.browser")?.let { add("--browser=$it")}
            })
        }

        register<YarnTask>("openGui") {
            dependsOn("yarn")
            group = "check"
            description = "Open functional tests GUI runner (Cypress)"

            val baseUrl = props.string("test.baseUrl") ?: main.environment.hosts.publish.url

            setWorkingDir(projectDir)
            setYarnCommand("cypress")
            setArgs(listOf("open", "-c", "baseUrl=$baseUrl"))
        }
    }
}
