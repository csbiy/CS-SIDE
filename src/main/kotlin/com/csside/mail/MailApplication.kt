package com.csside.mail

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MailApplication

fun main(args: Array<String>) {
	runApplication<MailApplication>(*args)
}

val METER = 1000

