plugins {
    id("base")
}

tasks {
    register<Zip>("zip") {
        from("src")
    }
    named("build") {
        dependsOn("zip")
    }
}
