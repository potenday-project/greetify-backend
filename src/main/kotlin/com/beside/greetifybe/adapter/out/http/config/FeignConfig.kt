package com.beside.greetifybe.adapter.out.http.config

import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients(
    basePackages = [
        "com.beside.greetifybe.adapter.out.http",
    ],
)
class FeignConfig
