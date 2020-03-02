plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    gradlePluginPortal()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
    implementation("com.neva.gradle:fork-plugin:4.2.0")
    implementation("com.netflix.nebula:nebula-project-plugin:7.0.4")
    implementation("com.cognifide.gradle:environment-plugin:0.1.7")
    implementation("com.cognifide.gradle:aem-plugin:11.0.0-beta2")
    implementation("org.apache.sling:org.apache.sling.caconfig.bnd-plugin:1.0.2")
    implementation("com.cognifide.gradle:lighthouse-plugin:1.0.0")
    implementation("com.github.node-gradle:gradle-node-plugin:2.2.0")
}
