package org.contribhub.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class ContribhubApplication

fun main(args: Array<String>) {
    runApplication<ContribhubApplication>(*args)
}
