import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version Version.SPRING_BOOT
    id("io.spring.dependency-management") version Version.SPRING_DEPENDENCY_MANAGEMENT
    kotlin("jvm") version Version.KOTLIN
    kotlin("plugin.spring") version Version.KOTLIN
}

group = "com.beside"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
}

extra["springCloudVersion"] = "2023.0.0-RC1"

dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-starter-validation:${Version.SPRING_BOOT}")
    implementation("org.springframework.boot:spring-boot-starter-web:${Version.SPRING_BOOT}")

    // spring openapi
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

    // logging
    implementation("io.github.oshai:kotlin-logging-jvm:5.1.0")

    // redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis:${Version.SPRING_BOOT}")

    // openfeign
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.0.1")

    // annotation processor
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:${Version.SPRING_BOOT}")

    // kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.10")

    // ulid
    implementation("com.github.f4b6a3:ulid-creator:5.2.2")

    // development support
    developmentOnly("org.springframework.boot:spring-boot-devtools:${Version.SPRING_BOOT}")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose:${Version.SPRING_BOOT}")

    // kotest
    testImplementation("org.springframework.boot:spring-boot-starter-test:${Version.SPRING_BOOT}")
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")

    // mockk
    testImplementation("io.mockk:mockk:1.13.8")
    testImplementation("com.ninja-squad:springmockk:4.0.2")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = Version.JAVA
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
