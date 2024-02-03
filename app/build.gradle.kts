plugins {
    java
    application
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "dev.hippy"
version = "0.0.1-SNAPSHOT"

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

    compileOnly("org.projectlombok:lombok")

    runtimeOnly("com.h2database:h2")

    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
    annotationProcessor("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

application {
    mainClass.set("dev.hippy.huntjob.HuntJobApplication")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
