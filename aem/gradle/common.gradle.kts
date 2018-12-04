subprojects {

    if (!project.path.startsWith(":aem:")) {
        return@subprojects
    }

    group = "com.company.example.aem"
    version = "1.0.0-SNAPSHOT"

}
