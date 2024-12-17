package org.contribhub.infra.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration
import org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.testcontainers.containers.PostgreSQLContainer
import javax.sql.DataSource

/**
 * Webclient 사용을 위한 spring-webflux 의존성으로 인해 webflux가 설정되면서 Spring Boot Test가 실행되지 않는 문제가 있음
 * TODO 가상 스레드를 적용하면 Webclient 의존성 제거 가능할 듯
 */
@EnableAutoConfiguration(exclude = [
    WebFluxAutoConfiguration::class,
    ErrorWebFluxAutoConfiguration::class,
    HttpHandlerAutoConfiguration::class
])
@TestConfiguration
class TestConfig {
    @Bean
    @ServiceConnection
    fun postgresContainer(): PostgreSQLContainer<*> {
        return PostgreSQLContainer<Nothing>("postgres:16-alpine")
            .apply {
                withDatabaseName("testdb")
                withDatabaseName("test")
                withDatabaseName("test")
            }
    }

    @Bean
    @Primary
    fun dataSource(postgresContainer: PostgreSQLContainer<*>): DataSource {
        return HikariDataSource(
            HikariConfig().apply {
                jdbcUrl = postgresContainer.jdbcUrl
                username = postgresContainer.username
                password = postgresContainer.password
                driverClassName = "org.postgresql.Driver"
            },
        )
    }
}
