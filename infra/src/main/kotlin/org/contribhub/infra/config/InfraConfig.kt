package org.contribhub.infra.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EnableJpaAuditing
@Import(WebClientConfig::class)
@ComponentScan("org.contribhub.infra")
class InfraConfig