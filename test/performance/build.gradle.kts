import com.moowork.gradle.node.npm.NpmTask

description = "Example - Performance Tests"

plugins {
    id("com.moowork.node")
}

tasks {
    register<NpmTask>("test") {
        dependsOn("npmInstall")
        setNpmCommand("run")
        setArgs(listOf("lighthouse"))
    }
}
