package com.daelim.springtest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class SpringTestApplication

fun main(args: Array<String>) {
	runApplication<SpringTestApplication>(*args)
}
