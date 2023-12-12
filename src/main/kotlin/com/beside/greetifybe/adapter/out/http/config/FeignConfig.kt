package com.beside.greetifybe.adapter.out.http.config

import feign.Logger
import feign.Retryer
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import java.util.concurrent.TimeUnit

@EnableFeignClients(
    basePackages = [
        "com.beside.greetifybe.adapter.out.http",
    ],
)
class FeignConfig {

    @Bean
    fun retryer(): Retryer.Default {
        return Retryer.Default(100L, TimeUnit.SECONDS.toMillis(3L), 5);
    }

    @Bean
    fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }

}
