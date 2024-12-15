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
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
    kotlin("plugin.jpa") version "1.9.25" // jpa 사용을 위한 플러그인 추가.
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework:spring-webflux")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${Versions.COROUTINE_VERSION}")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.JACKSON_KOTLIN}")

    // 로그 관련 라이브러리
    implementation("io.github.oshai:kotlin-logging-jvm:${Versions.LOGGER}")

    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.boot:spring-boot-starter-security")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testRuntimeOnly("org.postgresql:postgresql")

    // db연동관련 테스트를 위한 테스트 컨테이너 설정.
    testImplementation("org.testcontainers:testcontainers:${Versions.TEST_CONTAINER}")
    testImplementation("org.testcontainers:junit-jupiter:${Versions.TEST_CONTAINER}")
    testImplementation("org.testcontainers:postgresql:${Versions.TEST_CONTAINER}")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")

    // jpa 설정 추가.
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    runtimeOnly("org.postgresql:postgresql")

    // kotlin jdsl
    implementation("com.linecorp.kotlin-jdsl:jpql-dsl:${Versions.KOTLIN_JSDL}")
    implementation("com.linecorp.kotlin-jdsl:jpql-render:${Versions.KOTLIN_JSDL}")
    implementation("com.linecorp.kotlin-jdsl:spring-data-jpa-support:${Versions.KOTLIN_JSDL}")
    implementation("com.linecorp.kotlin-jdsl:hibernate-support:${Versions.KOTLIN_JSDL}")
//    implementation("com.linecorp.kotlin-jdsl:spring-data-kotlin-jdsl-start-jakarta:${Versions.KOTLIN_JSDL}")
//    implementation("com.linecorp.kotlin-jdsl:hibernate-kotlin-jdsl-jakarta:${Versions.KOTLIN_JSDL}")
}
// jpa사용을 위해 jpa사용 어노테이션에 대해서 open 처리.
allOpen {

    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

noArg {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

// plain jar 생성방지를 위한 설정추가
tasks.getByName<Jar>("jar") {
    enabled = false
}

tasks.test {
    systemProperty("spring.profiles.active", "test")
    // dependsOn(":core:test") TODO module: 주석 해제
}
