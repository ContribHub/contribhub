package org.contribhub.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ContribhubApplication

fun main(args: Array<String>) {
    runApplication<ContribhubApplication>(*args)
}
