object Versions {
    const val COROUTINE_VERSION = "1.8.1"
    const val JACKSON_KOTLIN = "2.17.0"
    const val KOTLIN_JSDL = "3.5.3"
    const val TEST_CONTAINER = "1.19.1"
    const val LOGGER = "7.0.0"
}

plugins {
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":infra"))

    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${Versions.COROUTINE_VERSION}")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.JACKSON_KOTLIN}")

    // 로그 관련 라이브러리
    implementation("io.github.oshai:kotlin-logging-jvm:${Versions.LOGGER}")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.boot:spring-boot-starter-security")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// plain jar 생성방지를 위한 설정추가
tasks.getByName<Jar>("jar") {
    enabled = false
}

tasks.test {
    systemProperty("spring.profiles.active", "test")
    dependsOn(":core:test")
    dependsOn(":infra:test")
}

tasks.jar {
    from(sourceSets.main.get().resources)  // resources를 JAR에 포함시킴
}