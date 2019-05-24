import com.cognifide.gradle.aem.AemExtension

allprojects {

    group = "com.company.example.aem"

    plugins.withId("com.cognifide.aem.common") {

        configure<AemExtension> {
            // amend if needed
        }
    }

    plugins.withId("com.cognifide.aem.bundle") {

        configure<AemExtension> {
            tasks {
                bundle {
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
        }
    }

}
