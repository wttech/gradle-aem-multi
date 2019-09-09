import com.cognifide.gradle.aem.AemExtension
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

            val baseUrl = props.string("baseUrl") ?: main.environment.hosts.publish.url

            setWorkingDir(projectDir)
            setYarnCommand("cypress")
            setArgs(listOf("run", "-c", "baseUrl=$baseUrl"))
        }
    }
}
