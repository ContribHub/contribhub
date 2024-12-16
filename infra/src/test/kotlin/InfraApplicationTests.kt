
import org.contribhub.infra.config.TestConfig
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@Import(TestConfig::class)
@ActiveProfiles("test")
class InfraApplicationTests {
    @Test
    fun contextLoads() {
    }
}
