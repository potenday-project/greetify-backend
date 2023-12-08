package com.beside.greetifybe.common.exception.handler

import java.time.LocalDateTime

data class ErrorResponse(
    val exceptionCode: String,
    val message: String,
    val timeStamp: LocalDateTime
)
