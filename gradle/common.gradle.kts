import com.cognifide.gradle.aem.common.AemExtension
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.moowork.gradle.node.NodeExtension

allprojects {

    repositories {
        jcenter()
        maven { url = uri("https://repo.adobe.com/nexus/content/groups/public") }
        maven { url = uri("https://repo1.maven.org/maven2") }
        maven { url = uri("https://dl.bintray.com/neva-dev/maven-public") }
        maven { url = uri("https://dl.bintray.com/kotlin/kotlin-dev") }
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
            failFast = true
            useJUnitPlatform()
            testLogging {
                events = setOf(TestLogEvent.FAILED)
                exceptionFormat = TestExceptionFormat.SHORT
            }

            dependencies {
                "testImplementation"("org.junit.jupiter:junit-jupiter-api:5.3.2")
                "testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine:5.3.2")
                "testImplementation"("io.wcm:io.wcm.testing.aem-mock.junit5:2.3.2")
            }
        }
    }

    plugins.withId("org.jetbrains.kotlin.jvm") {
        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    plugins.withId("com.moowork.node") {
        configure<NodeExtension> {
            version = "8.9.0"
            yarnVersion = "1.9.4"
            download = true
        }
    }

}


