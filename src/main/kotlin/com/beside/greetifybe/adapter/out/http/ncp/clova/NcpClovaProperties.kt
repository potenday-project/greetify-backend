package com.beside.greetifybe.adapter.out.http.ncp.clova

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "ncp.clova-studio")
data class NcpClovaProperties @ConstructorBinding constructor(
    val clovaStudioApiKey: String,
    val apiGatewayKey: String,
    val clovaStudioRequestId: String,
)
