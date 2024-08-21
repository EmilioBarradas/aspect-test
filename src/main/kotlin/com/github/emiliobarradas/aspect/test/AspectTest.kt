package com.github.emiliobarradas.aspect.test

import io.micrometer.core.annotation.Timed
import io.micrometer.core.aop.CountedAspect
import io.micrometer.core.aop.TimedAspect
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableLoadTimeWeaving
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

fun main() {
    runApplication<AspectTest>()
}

@SpringBootApplication
@EnableLoadTimeWeaving
class AspectTest

@Configuration
class MetricsConfig {

    @Bean
    fun timedAspect(registry: MeterRegistry): TimedAspect {
        return TimedAspect(registry)
    }

    @Bean
    fun countedAspect(registry: MeterRegistry): CountedAspect {
        return CountedAspect(registry)
    }

}

@Configuration
class ClientConfig {

    @Bean
    fun client(): Client {
        return Client()
    }

}

@RestController
@RequestMapping("/test")
class Controller(private val client: Client) {

    @GetMapping
    fun test() {
        client.test()
    }

}

class Client {

    @Timed
    fun test() {
        if (Random.nextDouble() > 0.5) {
            error("Test")
        } else {
            println("Test")
        }
    }

}
