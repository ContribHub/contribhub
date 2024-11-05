package org.contribhub.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class ContribhubApplication

fun main(args: Array<String>) {
    runApplication<ContribhubApplication>(*args)
}
