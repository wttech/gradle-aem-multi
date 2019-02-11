import com.cognifide.gradle.aem.common.AemExtension

allprojects {

    group = "com.company.example.aem"

    plugins.withId("com.cognifide.aem.config") {

        configure<AemExtension> {
            config {
                // amend if needed
            }
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

            "compileOnly"( "org.osgi:osgi.cmpn:6.0.0")
            "compileOnly"( "org.osgi:org.osgi.core:6.0.0")
            "compileOnly"( "javax.servlet:servlet-api:2.5")
            "compileOnly"( "javax.servlet:jsp-api:2.0")
            "compileOnly"( "javax.jcr:jcr:2.0")
            "compileOnly"( "org.slf4j:slf4j-api:1.7.25")
            "compileOnly"( "org.apache.geronimo.specs:geronimo-atinject_1.0_spec:1.0")
            "compileOnly"( "org.apache.sling:org.apache.sling.api:2.16.4")
            "compileOnly"( "org.apache.sling:org.apache.sling.jcr.api:2.4.0")
            "compileOnly"( "org.apache.sling:org.apache.sling.models.api:1.3.6")
            "compileOnly"( "org.apache.sling:org.apache.sling.settings:1.3.8")
            "compileOnly"( "com.google.guava:guava:15.0")
            "compileOnly"( "com.google.code.gson:gson:2.8.2")
            "compileOnly"( "joda-time:joda-time:2.9.1")
            "compileOnly"("org.jetbrains:annotations:13.0")

            "compileOnly"("com.adobe.aem:uber-jar:6.4.0:obfuscated-apis")

            // Extra libraries provided by: task "aemSatisfy", task aemCompose.fromJar, put directly inside dir 'install'.

            "compileOnly"("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.20")
            "compileOnly"("org.hashids:hashids:1.0.1")
        }
    }

}
