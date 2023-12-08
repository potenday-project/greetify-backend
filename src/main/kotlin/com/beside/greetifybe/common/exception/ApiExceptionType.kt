package com.beside.greetifybe.common.exception

enum class ApiExceptionType(override val code: String) : CustomExceptionType {
    INVALID_DATE_EXCEPTION("API-001"),
    INVALID_PARAMETER("API-002"),
    RESOURCE_NOT_FOUND("API-003"),
}
