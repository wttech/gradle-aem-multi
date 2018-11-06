import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.cognifide.gradle.aem.api.AemExtension
import com.cognifide.gradle.aem.instance.SatisfyTask
import com.cognifide.gradle.aem.instance.SetupTask
import com.moowork.gradle.node.NodeExtension
import com.neva.gradle.fork.ForkTask

plugins {
    id("com.neva.fork")
    id("com.cognifide.aem.instance")
    id("com.moowork.node") apply false
    kotlin("jvm") apply false
}

description = "Example"
defaultTasks = listOf(":deploy")

aem {
    tasks {
        setupSequence("deploy") { dependsOn(
            path(":aem.full:aemDeploy"),
            await("full"),
            path(":aem.migration:aemDeploy"),
            await("migration"),
            path(":test.integration:test"),
            path(":test.functional:test")
        )}
    }
}

tasks.named<SatisfyTask>(SatisfyTask.NAME).configure {
    packages {
        group("dep.vanity-urls") { /* local("pkg/vanityurls-components-1.0.2.zip") */ }
        group("dep.kotlin") { dependency("org.jetbrains.kotlin:kotlin-osgi-bundle:1.3.0") }
        group("dep.acs-aem-commons") { url("https://github.com/Adobe-Consulting-Services/acs-aem-commons/releases/download/acs-aem-commons-3.17.0/acs-aem-commons-content-3.17.0-min.zip") }
        group("tool.aem-easy-content-upgrade") { url("https://github.com/valtech/aem-easy-content-upgrade/releases/download/1.4.0/aecu.bundle-1.4.0.zip") }
        group("tool.search-webconsole-plugin") { dependency("com.neva.felix:search-webconsole-plugin:1.2.0") }
    }
}

tasks.named<ForkTask>(ForkTask.NAME).configure {
    config {
        cloneFiles()
        moveFiles(mapOf(
                "/com/company/aem/example" to "/{{projectGroup|substitute('.', '/')}}/{{projectName}}",
                "/example" to "/{{projectName}}"
        ))
        replaceContents(mapOf(
                "com.company.aem.example" to "{{projectGroup}}.{{projectName}}",
                "com.company.aem" to "{{projectGroup}}",
                "Example" to "{{projectLabel}}",
                "example" to "{{projectName}}"
        ))
    }
}

allprojects {

    val subproject = this@allprojects

    group = "com.company.example"
    version = "1.0.0-SNAPSHOT"

    repositories {
        jcenter()
        maven { url = uri("https://repo.adobe.com/nexus/content/groups/public") }
        maven { url = uri("https://repo1.maven.org/maven2") }
        maven { url = uri("https://dl.bintray.com/neva-dev/maven-public") }
    }

    plugins.withId("com.cognifide.aem.base") {

        // Common AEM configuration (CRX packages, deployment etc)

        aem {
            config {

                // https://github.com/Cognifide/gradle-aem-plugin#additional

            }
        }

    }

    plugins.withId("com.cognifide.aem.bundle") {

        // Unified bundle configuration

        aem {
            bundle {
                attribute("Bundle-Category", "example")
                attribute("Bundle-Vendor", "Company")
            }
        }

        subproject.dependencies {

            // AEM runtime dependencies

            "compileOnly"("org.osgi:osgi.cmpn:6.0.0")
            "compileOnly"("org.osgi:org.osgi.core:6.0.0")

            "compileOnly"("javax.servlet:servlet-api:2.5")
            "compileOnly"("javax.jcr:jcr:2.0")
            "compileOnly"("org.slf4j:slf4j-api:1.7.25")
            "compileOnly"("org.apache.geronimo.specs:geronimo-atinject_1.0_spec:1.0")
            // TODO fix it (no such package in 1.x version on instance)
            "compileOnly"("org.apache.geronimo.specs:geronimo-annotation_1.0_spec:1.1.1")

            "compileOnly"("org.apache.sling:org.apache.sling.api:2.16.4")
            "compileOnly"("org.apache.sling:org.apache.sling.jcr.api:2.4.0")
            "compileOnly"("org.apache.sling:org.apache.sling.models.api:1.3.6")
            "compileOnly"("com.google.code.gson:gson:2.8.1")
            "compileOnly"(group = "com.adobe.aem", name = "uber-jar", version = "6.4.0", classifier = "obfuscated-apis")
            "compileOnly"("joda-time:joda-time:2.9.1")
            "compileOnly"("org.jetbrains:annotations:13.0")

            // Extra libraries provided by packages through task "aemSatisfy"
            // or configurations: "aemEmbed", "aemInstall".

            "compileOnly"("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.0")
            "compileOnly"("org.jetbrains.kotlin:kotlin-reflect:1.3.0")
            "compileOnly"("org.hashids:hashids:1.0.1")
        }
    }

    plugins.withId("org.jetbrains.kotlin.jvm") {
        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
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
            failFast = true

            subproject.dependencies {
                "testImplementation"("org.junit.jupiter:junit-jupiter-api:5.1.1")
                "testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine:5.1.1")
                "testImplementation"("io.wcm:io.wcm.testing.aem-mock.junit5:2.3.2")
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


