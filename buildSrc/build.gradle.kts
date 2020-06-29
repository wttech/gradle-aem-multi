repositories {
    mavenLocal()
    jcenter()
    gradlePluginPortal()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
    implementation("com.neva.gradle:fork-plugin:5.0.3")
    implementation("com.netflix.nebula:nebula-project-plugin:7.0.9")
    implementation("com.cognifide.gradle:environment-plugin:1.0.0")
    implementation("com.cognifide.gradle:aem-plugin:14.0.2")
    implementation("org.apache.sling:org.apache.sling.caconfig.bnd-plugin:1.0.2")
    implementation("com.cognifide.gradle:lighthouse-plugin:1.0.0")
    implementation("com.github.node-gradle:gradle-node-plugin:2.2.4")
    implementation("net.researchgate:gradle-release:2.6.0")
}
