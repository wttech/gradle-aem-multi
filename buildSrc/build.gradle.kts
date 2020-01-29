plugins {
    `kotlin-dsl`
}

repositories {
    mavenLocal()
    jcenter()
    gradlePluginPortal()
    maven("http://dl.bintray.com/cognifide/maven-public")
    maven("https://dl.bintray.com/neva-dev/maven-public")
}

dependencies {
    implementation("com.cognifide.gradle:common-plugin:1.0.0")
    implementation("com.cognifide.gradle:environment-plugin:1.0.0")
    implementation("com.cognifide.gradle:aem-plugin:10.1.5")
    implementation("org.apache.sling:org.apache.sling.caconfig.bnd-plugin:1.0.2")
    implementation("com.cognifide.gradle:lighthouse-plugin:1.0.0")
    implementation("com.neva.gradle:fork-plugin:4.2.0")
    implementation("com.github.node-gradle:gradle-node-plugin:2.2.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
    implementation("com.netflix.nebula:nebula-project-plugin:7.0.4")
}
