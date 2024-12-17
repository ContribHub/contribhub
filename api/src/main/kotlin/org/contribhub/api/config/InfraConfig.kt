package org.contribhub.api.config

import org.contribhub.infra.config.InfraConfig
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(InfraConfig::class)
class InfraConfig
