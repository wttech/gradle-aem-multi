/**
 * Common configuration for AEM application artifacts
 */
import com.cognifide.gradle.aem.AemExtension
import com.cognifide.gradle.aem.bundle.tasks.bundle

group = "com.company.example"

plugins.withId("com.cognifide.aem.common") {
    configure<AemExtension> {
        `package` {
            appPath.set("/apps/example")
            commonDir.set(rootProject.file("app/aem/common/package"))
            validator {
                base("com.adobe.acs:acs-aem-commons-oakpal-checks:4.3.4")
            }
        }
    }
}

plugins.withId("com.cognifide.aem.bundle") {
    tasks {
        withType<Jar> {
            bundle {
                category = "example"
                vendor = "Company"
                bnd("-plugin org.apache.sling.caconfig.bndplugin.ConfigurationClassScannerPlugin")
            }
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
        "compileOnly"("org.osgi:org.osgi.framework:1.9.0")
        "compileOnly"("org.osgi:org.osgi.resource:1.0.0")
        "compileOnly"("org.slf4j:slf4j-api:1.7.21")
        "compileOnly"("javax.jcr:jcr:2.0")
        "compileOnly"("javax.servlet:javax.servlet-api:3.1.0")
        "compileOnly"("javax.annotation:javax.annotation-api:1.3.2")
        "compileOnly"("org.apache.sling:org.apache.sling.servlets.annotations:1.2.4")
        "compileOnly"("org.apache.sling:org.apache.sling.models.api:1.3.6")

        "compileOnly"("com.adobe.aem:uber-jar:6.5.0:apis")

        // Extra libraries provided by: task "instanceSatisfy", task packageCompose.fromJar
        // or put directly inside dir 'install'.

        "compileOnly"("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.71")
        "compileOnly"("org.hashids:hashids:1.0.1")

        // AEM test dependencies

        "testImplementation"("io.wcm:io.wcm.testing.aem-mock.junit5:2.5.2")
    }
}
