package org.contribhub.api

import org.contribhub.api.config.TestConfig
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
@Import(TestConfig::class)
class ContribhubApplicationTests {
    @Test
    fun contextLoads() {
    }
}
