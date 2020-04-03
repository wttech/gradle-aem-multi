import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.moowork.gradle.node.NodeExtension

repositories {
    jcenter()
}

plugins.withId("java") {
    tasks.withType<JavaCompile>().configureEach {
        with(options) {
            sourceCompatibility = "1.8"
            targetCompatibility = "1.8"
            encoding = "UTF-8"
        }
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
            exceptionFormat = TestExceptionFormat.SHORT
        }
    }

    dependencies {
        "testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine:5.6.0")
        "testImplementation"("org.junit.jupiter:junit-jupiter-api:5.6.0")
    }
}

plugins.withId("org.jetbrains.kotlin.jvm") {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

plugins.withId("com.github.node-gradle.node") {
    configure<NodeExtension> {
        version = "10.16.3"
        yarnVersion = "1.21.1"
        download = true
    }
}

