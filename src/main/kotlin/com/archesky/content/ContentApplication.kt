package com.archesky.chat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages=[
    "com.archesky.chat",
    "com.archesky.auth.library.security",
    "com.archesky.auth.library.service",
    "com.archesky.ssl.library.configuration"
])
class ChatApplication

fun main(args: Array<String>) {
    runApplication<ChatApplication>(*args)
}
