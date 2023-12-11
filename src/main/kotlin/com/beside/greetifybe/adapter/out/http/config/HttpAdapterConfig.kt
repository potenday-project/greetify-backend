package com.beside.greetifybe.adapter.out.http.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    FeignConfig::class,
)
class HttpAdapterConfig
