package com.jaadla.namlogapi

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class NamLogApiApplication {
}

fun main(args: Array<String>) {
    SpringApplication.run(NamLogApiApplication::class.java, *args)
}
