import com.cognifide.gradle.aem.AemExtension
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.cognifide.gradle.aem.bundle.tasks.BundleCompose
import com.moowork.gradle.node.NodeExtension

repositories {
    jcenter()
    maven("https://repo.adobe.com/nexus/content/groups/public")
    maven("https://repo1.maven.org/maven2")
    maven("https://dl.bintray.com/neva-dev/maven-public")
    maven("https://dl.bintray.com/acs/releases")
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

    tasks {
        withType<BundleCompose> {
            category = "example"
            vendor = "Company"
            bnd("-plugin org.apache.sling.caconfig.bndplugin.ConfigurationClassScannerPlugin")
        }
    }

    dependencies {

        // AEM runtime dependencies

        "compileOnly"("org.osgi:org.osgi.annotation.versioning:1.1.0")
        "compileOnly"("org.osgi:org.osgi.annotation.bundle:1.0.0")
        "compileOnly"("org.osgi:org.osgi.service.metatype.annotations:1.4.0")
        "compileOnly"("org.osgi:org.osgi.service.component.annotations:1.4.0")
        "compileOnly"("org.osgi:org.osgi.service.component:1.4.0")
        "compileOnly"("org.osgi:org.osgi.service.cm:1.6.0")
        "compileOnly"("org.osgi:org.osgi.service.event:1.3.1")
        "compileOnly"("org.osgi:org.osgi.service.log:1.4.0")
        "compileOnly"("org.osgi:org.osgi.resource:1.0.0")
        "compileOnly"("org.osgi:org.osgi.framework:1.9.0")
        "compileOnly"("org.apache.sling:org.apache.sling.models.api:1.3.6")
        "compileOnly"("javax.servlet:javax.servlet-api:3.1.0")
        "compileOnly"("javax.servlet.jsp:jsp-api:2.1")
        "compileOnly"("javax.annotation:javax.annotation-api:1.3.2")
        "compileOnly"("javax.jcr:jcr:2.0")
        "compileOnly"("com.day.cq.wcm:cq-wcm-taglib:5.7.4")
        "compileOnly"("org.slf4j:slf4j-api:1.7.25")
        "compileOnly"("com.adobe.cq:core.wcm.components.core:2.8.0")
        "compileOnly"("com.adobe.aem:uber-jar:${Build.AEM_VERSION}:apis")

        // Extra libraries provided by: task "instanceSatisfy", task packageCompose.fromJar
        // or put directly inside dir 'install'.

        "compileOnly"("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Build.KOTLIN_VERSION}")
        "compileOnly"("org.hashids:hashids:1.0.1")

        // AEM test dependencies

        "testImplementation"("io.wcm:io.wcm.testing.aem-mock.junit5:2.5.2")
    }
}
