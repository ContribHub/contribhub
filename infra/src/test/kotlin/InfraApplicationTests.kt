
import org.contribhub.infra.config.InfraConfig
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [InfraConfig::class])
class InfraApplicationTests {
    @Test
    fun contextLoads() {
    }
}
