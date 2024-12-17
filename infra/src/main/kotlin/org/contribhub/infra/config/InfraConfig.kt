package org.contribhub.infra.config

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.PropertySource

@Configuration
@ComponentScan("org.contribhub.infra")
@Import(WebClientConfig::class, JpaConfig::class)
@PropertySource(
    value = ["classpath:application-infra.yml", "classpath:application-infra-\${spring.profiles.active}.yml"],
    ignoreResourceNotFound = true,
    factory = YamlPropertySourceFactory::class
)
@EnableAutoConfiguration
class InfraConfig