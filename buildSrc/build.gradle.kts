repositories {
    mavenLocal()
    jcenter()
    gradlePluginPortal()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
    implementation("com.neva.gradle:fork-plugin:5.0.1")
    implementation("com.netflix.nebula:nebula-project-plugin:7.0.9")
    implementation("com.cognifide.gradle:environment-plugin:0.1.13")
    implementation("com.cognifide.gradle:aem-plugin:14.0.0-beta")
    implementation("org.apache.sling:org.apache.sling.caconfig.bnd-plugin:1.0.2")
    implementation("com.cognifide.gradle:lighthouse-plugin:1.0.0")
    implementation("com.github.node-gradle:gradle-node-plugin:2.2.3")
    implementation("net.researchgate:gradle-release:2.6.0")
}
