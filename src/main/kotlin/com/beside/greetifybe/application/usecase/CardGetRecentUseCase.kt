package com.beside.greetifybe.application.usecase

import com.beside.greetifybe.domain.Card
import com.beside.greetifybe.domain.vo.IPAddress

fun interface CardGetRecentUseCase {

    fun invoke(ipAddress: IPAddress): Result

    sealed class Result {
        data class Success(val card: Card) : Result()
        data class Failure(val message: String) : Result()
    }
}
