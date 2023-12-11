package com.beside.greetifybe.common.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan(basePackages = ["com.beside.greetifybe.adapter.out.http"])
class AppConfig
