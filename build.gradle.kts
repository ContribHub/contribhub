import org.contribhub.Versions

plugins {
    kotlin("jvm") version "1.9.25"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"

    java
}

allprojects {
    group = "org.contribhub"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINE_VERSION}")

        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
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
}

tasks.build {
    dependsOn(
        ":core:ktlintFormat",
        ":core:ktlintFormat",
        ":api:ktlintFormat",
        ":api:ktlintCheck",
        ":infra:ktlintCheck",
        ":infra:ktlintCheck",
    )
}
