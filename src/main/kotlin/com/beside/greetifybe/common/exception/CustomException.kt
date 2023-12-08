package com.beside.greetifybe.common.exception

class CustomException(val type: CustomExceptionType, override val message: String) : RuntimeException(message)
