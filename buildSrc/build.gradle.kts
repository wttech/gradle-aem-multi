plugins {
    `kotlin-dsl`
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

repositories {
    mavenLocal()
    jcenter()
    gradlePluginPortal()
    maven { url = uri("http://dl.bintray.com/cognifide/maven-public") }
    maven { url = uri("https://dl.bintray.com/neva-dev/maven-public") }
}

dependencies {
    implementation("com.cognifide.gradle:aem-plugin:8.0.0")
    implementation("com.cognifide.gradle:lighthouse-plugin:1.0.0")
    implementation("com.neva.gradle:fork-plugin:3.1.6")
    implementation("com.github.node-gradle:gradle-node-plugin:2.1.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.50")
}
