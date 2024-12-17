package org.contribhub.infra.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EntityScan("org.contribhub.infra.repository.entity")
@EnableJpaAuditing
@EnableJpaRepositories("org.contribhub.infra.repository")
class JpaConfig
