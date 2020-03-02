plugins {
    id("base")
}

apply(from = rootProject.file("app/common.gradle.kts"))
apply(from = rootProject.file("app/aem/common.gradle.kts"))

tasks {
    register<Zip>("zip") {
        from("src")
    }
    named("build") {
        dependsOn("zip")
    }
}
