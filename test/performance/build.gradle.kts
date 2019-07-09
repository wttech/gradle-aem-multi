import com.moowork.gradle.node.npm.NpmTask

description = "Example - Performance Tests"

plugins {
    id("com.moowork.node")
}
node {
    //Lighthouse requires Node 10.13 or later
    version = "10.16.0"
    download = true
}

tasks {
    register<NpmTask>("test") {
        dependsOn("npmInstall")
        setNpmCommand("run")
        setArgs(listOf("lighthouse"))
    }
}
