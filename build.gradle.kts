plugins {
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    id("io.freefair.aspectj.post-compile-weaving") version "8.10"
}

group = "com.github.emiliobarradas"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.spring.io/milestone")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.aspectj:aspectjrt:1.9.22.1")
    implementation("io.micrometer:micrometer-core:1.14.0-M2")
    aspect("io.micrometer:micrometer-core:1.14.0-M2") {
        exclude(group = "io.micrometer", module = "micrometer-observation")
    }
}

kotlin {
    jvmToolchain(21)
}
