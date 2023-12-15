package com.beside.greetifybe.common.exception.handler

import com.beside.greetifybe.common.exception.ApiExceptionType
import com.beside.greetifybe.common.exception.CustomException
import com.beside.greetifybe.common.exception.SystemExceptionType
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.DateTimeException
import java.time.LocalDateTime

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class GlobalExceptionHandler {

    private val logger = KotlinLogging.logger { }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomException::class)
    fun handleApiException(e: CustomException): ErrorResponse {
        return ErrorResponse(
            exceptionCode = e.type.code,
            message = e.message,
            timeStamp = LocalDateTime.now()
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateTimeException::class)
    fun handleDateTimeException(e: DateTimeException): ErrorResponse {
        return ErrorResponse(
            exceptionCode = ApiExceptionType.INVALID_DATE_EXCEPTION.code,
            message = e.message!!,
            timeStamp = LocalDateTime.now()
        )
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpMethodNotSupportedException(exception: HttpRequestMethodNotSupportedException): ErrorResponse {
        return ErrorResponse(
            exceptionCode = ApiExceptionType.INVALID_PARAMETER.code,
            message = "지원하지 않는 Http Method 입니다.",
            timeStamp = LocalDateTime.now()
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMethodNotReadableException(exception: HttpMessageNotReadableException): ErrorResponse {
        logger.info { exception.message }
        return ErrorResponse(
            exceptionCode = ApiExceptionType.INVALID_PARAMETER.code,
            message = "잘못된 HttpBody 형식입니다.",
            timeStamp = LocalDateTime.now()
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleRequestValidException(exception: MethodArgumentNotValidException): ErrorResponse {
        val builder = StringBuilder()
        for (fieldError in exception.bindingResult.fieldErrors) {
            builder
                .append("[${fieldError.field}](은)는 ${fieldError.defaultMessage}")
                .append("입력된 값: ${fieldError.rejectedValue}")
                .append("|")
        }
        val message = builder.toString()
        return ErrorResponse(
            exceptionCode = ApiExceptionType.INVALID_PARAMETER.code,
            message = message.substring(0, message.lastIndexOf("|")),
            timeStamp = LocalDateTime.now()
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException::class)
    fun handleApiException(e: RuntimeException, request: HttpServletRequest): ErrorResponse {
        logger.error { e.stackTraceToString() }
        return ErrorResponse(
            exceptionCode = SystemExceptionType.RUNTIME_EXCEPTION.code,
            message = "Internal Server Error",
            timeStamp = LocalDateTime.now()
        )
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable::class)
    fun handleApiException(e: Throwable, request: HttpServletRequest): ErrorResponse {
        logger.error { e.stackTraceToString() }

        return ErrorResponse(
            exceptionCode = SystemExceptionType.INTERNAL_SERVER_ERROR.code,
            message = "Internal Server Error",
            timeStamp = LocalDateTime.now()
        )
    }

}
