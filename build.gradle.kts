plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories(RepositoryHandler::mavenCentral)

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("com.codeborne:selenide:7.0.2")
    testImplementation("io.rest-assured:rest-assured:5.3.0")
    testImplementation("org.apache.logging.log4j:log4j-core:2.17.1")
    testImplementation("com.github.javafaker:javafaker:1.0.2")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.13.0")
    //testImplementation("org.apache.logging.log4j:log4j-core:3.0.0-alpha1")
}

tasks.test {
    useJUnitPlatform()
}