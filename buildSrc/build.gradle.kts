repositories {
    mavenLocal()
    jcenter()
    maven { url = uri("https://plugins.gradle.org/m2/") }
    maven { url = uri("http://dl.bintray.com/cognifide/maven-public") }
    maven { url = uri("https://dl.bintray.com/neva-dev/maven-public") }
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-dev") } // TODO use stable
}


dependencies {
    implementation("com.cognifide.gradle:aem-plugin:6.0.0")
    implementation("com.neva.gradle:fork-plugin:1.0.7")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.20-dev-998") // TODO use stable
    implementation("com.moowork.gradle:gradle-node-plugin:1.2.0")
}
