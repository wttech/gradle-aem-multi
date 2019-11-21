import com.cognifide.gradle.aem.AemExtension
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.moowork.gradle.node.NodeExtension

version = "1.0.0-SNAPSHOT"

repositories {
    jcenter()
    maven { url = uri("https://repo.adobe.com/nexus/content/groups/public") }
    maven { url = uri("https://repo1.maven.org/maven2") }
    maven { url = uri("https://dl.bintray.com/neva-dev/maven-public") }
    maven { url = uri("https://dl.bintray.com/acs/releases") }
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
    }

    dependencies {
        "testImplementation"("org.junit.jupiter:junit-jupiter-api:5.3.2")
        "testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine:5.3.2")
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
        yarnVersion = "1.19.1"
        download = true
    }
}

plugins.withId("com.cognifide.aem.package") {

    configure<AemExtension> {
        `package` {
            validator {
                base("com.adobe.acs:acs-aem-commons-oakpal-checks:4.3.4")
            }
        }
    }

}

plugins.withId("com.cognifide.aem.bundle") {

    configure<AemExtension> {
        tasks {
            bundleCompose {
                category = "example"
                vendor = "Company"
            }
        }
    }

    dependencies {

        // AEM runtime dependencies

        "compileOnly"("org.osgi:osgi.cmpn:6.0.0")
        "compileOnly"("org.osgi:org.osgi.core:6.0.0")
        "compileOnly"("javax.servlet:servlet-api:2.5")
        "compileOnly"("javax.servlet:jsp-api:2.0")
        "compileOnly"("javax.jcr:jcr:2.0")
        "compileOnly"("org.slf4j:slf4j-api:1.7.25")
        "compileOnly"("org.apache.geronimo.specs:geronimo-atinject_1.0_spec:1.0")
        "compileOnly"("org.apache.sling:org.apache.sling.api:2.18.4")
        "compileOnly"("org.apache.sling:org.apache.sling.jcr.api:2.4.0")
        "compileOnly"("org.apache.sling:org.apache.sling.models.api:1.3.8")
        "compileOnly"("org.apache.sling:org.apache.sling.settings:1.3.10")
        "compileOnly"("com.google.guava:guava:15.0")
        "compileOnly"("com.google.code.gson:gson:2.8.5")
        "compileOnly"("joda-time:joda-time:2.9.1")
        "compileOnly"("org.jetbrains:annotations:13.0")

        "compileOnly"("com.adobe.aem:uber-jar:${Build.AEM_VERSION}:apis")

        // Extra libraries provided by: task "instanceSatisfy", task packageCompose.fromJar
        // or put directly inside dir 'install'.

        "compileOnly"("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Build.KOTLIN_VERSION}")
        "compileOnly"("org.hashids:hashids:1.0.1")

        // AEM test dependencies

        "testImplementation"("io.wcm:io.wcm.testing.aem-mock.junit5:2.3.2")
    }
}
