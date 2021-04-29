import com.epages.restdocs.apispec.gradle.OpenApi3Extension

dependencies {
    runtimeOnly("mysql:mysql-connector-java")
}

configure<OpenApi3Extension> {
    setServer(rootProject.property("openapi.url") as String + ":8081")
    title = "${rootProject.name}-${project.name}"
    version = project.version as String
    description = """
                  | Review front API
                  """.trimMargin()
    format = "yml"
    separatePublicApi = true
    outputFileNamePrefix = "${rootProject.name}-${project.name}"
}
