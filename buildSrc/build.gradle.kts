repositories {
    mavenLocal()
    jcenter()
    maven { url = uri("https://plugins.gradle.org/m2/") }
    maven { url = uri("http://dl.bintray.com/cognifide/maven-public") }
    maven { url = uri("https://dl.bintray.com/neva-dev/maven-public") }
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-dev") } // TODO remove after releasing 1.3.20
}

dependencies {
    implementation("com.cognifide.gradle:aem-plugin:6.0.0")
    implementation("com.neva.gradle:fork-plugin:1.0.8")
    implementation("com.moowork.gradle:gradle-node-plugin:1.2.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.20-dev-998") // TODO update when released 1.3.20
}
