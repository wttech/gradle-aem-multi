plugins {
    id("com.cognifide.aem.bundle")
    id("maven-publish")
}

apply(from = rootProject.file("app/common.gradle.kts"))
apply(from = rootProject.file("app/aem/common.gradle.kts"))

description = "Example - Core"

dependencies {
    testImplementation("uk.org.lidalia:slf4j-test:1.0.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.1")
    testImplementation("org.mockito:mockito-core:2.25.1")
    testImplementation("org.mockito:mockito-junit-jupiter:2.25.1")
    testImplementation("junit-addons:junit-addons:1.4")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
