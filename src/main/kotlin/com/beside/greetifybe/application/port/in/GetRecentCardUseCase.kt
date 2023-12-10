package com.beside.greetifybe.application.port.`in`

import com.beside.greetifybe.common.exception.CustomException
import com.beside.greetifybe.domain.Card
import com.beside.greetifybe.domain.vo.IPAddress

fun interface GetRecentCardUseCase {

    fun invoke(ipAddress: IPAddress): Result

    sealed class Result {
        data class Success(val card: Card) : Result()
        data class Failure(val exception: CustomException) : Result()
    }
}
