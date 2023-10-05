plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories(RepositoryHandler::mavenCentral)

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.codeborne:selenide:6.18.0")
    testImplementation("io.rest-assured:rest-assured:5.3.2")
}

tasks.test {
    useJUnitPlatform()
}