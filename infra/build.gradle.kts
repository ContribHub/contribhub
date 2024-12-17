import org.contribhub.Versions

plugins {
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("plugin.jpa") version "1.9.25" // jpa 사용을 위한 플러그인 추가.
}

dependencies {
    implementation(project(":core"))

    // kotlin jdsl
    implementation("com.linecorp.kotlin-jdsl:jpql-dsl:${Versions.KOTLIN_JSDL}")
    implementation("com.linecorp.kotlin-jdsl:jpql-render:${Versions.KOTLIN_JSDL}")
    implementation("com.linecorp.kotlin-jdsl:spring-data-jpa-support:${Versions.KOTLIN_JSDL}")
    implementation("com.linecorp.kotlin-jdsl:hibernate-support:${Versions.KOTLIN_JSDL}")
//    implementation("com.linecorp.kotlin-jdsl:spring-data-kotlin-jdsl-start-jakarta:${Versions.KOTLIN_JSDL}")
//    implementation("com.linecorp.kotlin-jdsl:hibernate-kotlin-jdsl-jakarta:${Versions.KOTLIN_JSDL}")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework:spring-webflux")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${Versions.COROUTINE_VERSION}")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.JACKSON_KOTLIN}")

    // 로그 관련 라이브러리
    implementation("io.github.oshai:kotlin-logging-jvm:${Versions.LOGGER}")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // db연동관련 테스트를 위한 테스트 컨테이너 설정.
    testImplementation("org.testcontainers:testcontainers:${Versions.TEST_CONTAINER}")
    testImplementation("org.testcontainers:junit-jupiter:${Versions.TEST_CONTAINER}")
    testImplementation("org.testcontainers:postgresql:${Versions.TEST_CONTAINER}")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")

    // jpa 설정 추가.
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    runtimeOnly("org.postgresql:postgresql")
    /**
     * api 모듈 테스트 시 h2 db 사용을 위한 라이브러리
     * infra 모듈 테스트 시에는 testcontainer를 사용 (testcontainer 설정이 infra.test 패키지에 있어, api 모듈의 spring boot 테스트 시 db 설정을 할 수 없어 추가됨)
     */
    runtimeOnly("com.h2database:h2:2.3.232")
}

tasks.test {
    systemProperty("spring.profiles.active", "test")
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

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}