plugins {
    java
    application
    checkstyle
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "dev.hippy"
version = "1.1.1"

repositories {
    mavenLocal()
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    implementation("org.instancio:instancio-junit:3.3.1")
    implementation("net.datafaker:datafaker:2.0.2")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

    compileOnly("org.projectlombok:lombok")

    runtimeOnly("com.h2database:h2")

    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
    testImplementation("net.javacrumbs.json-unit:json-unit-assertj:3.2.2")
}

application {
    mainClass.set("dev.hippy.huntjob.HuntJobApplication")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
