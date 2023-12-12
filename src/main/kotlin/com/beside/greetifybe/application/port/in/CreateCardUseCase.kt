package com.beside.greetifybe.application.port.`in`

import com.beside.greetifybe.domain.Card
import com.beside.greetifybe.domain.vo.IPAddress

fun interface CreateCardUseCase {

    fun invoke(command: Command): Result

    data class Command(
        val userIp: IPAddress,
        val cardDesignId: Long,
        val phrase: String,
    )

    sealed class Result {
        data class Success(val card: Card) : Result()
    }

}
