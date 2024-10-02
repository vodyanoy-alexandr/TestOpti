plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.1")

    // Selenide для автотестов
    testImplementation("com.codeborne:selenide:7.5.1")

    // Rest-Assured для API тестов
    testImplementation("io.rest-assured:rest-assured:5.5.0")

    // Log4j для логгирования
    testImplementation("org.apache.logging.log4j:log4j-core:2.24.1")

    // Java Faker для генерации данных
    testImplementation("com.github.javafaker:javafaker:1.0.2")

    // Jackson для работы с JSON
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.18.0")

    // WebDriverManager для автоматического управления драйверами
    testImplementation("io.github.bonigarcia:webdrivermanager:5.9.2")

    // SLF4J Simple для устранения предупреждений о логировании
    testImplementation("org.slf4j:slf4j-simple:1.7.36")
}

tasks.test {
    useJUnitPlatform()
}
