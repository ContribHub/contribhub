object Versions {
    const val COROUTINE_VERSION = "1.8.1"
    const val JACKSON_KOTLIN = "2.17.0"
}

plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
}

group = "org.contribhub"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework:spring-webflux")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${Versions.COROUTINE_VERSION}")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.JACKSON_KOTLIN}")

    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.build {
    dependsOn("ktlintCheck")
}
