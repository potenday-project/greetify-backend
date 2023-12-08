package com.beside.greetifybe.application.usecase

import com.beside.greetifybe.domain.enums.Age
import com.beside.greetifybe.domain.enums.Dialect
import com.beside.greetifybe.domain.enums.Emotional
import com.beside.greetifybe.domain.enums.Season
import com.beside.greetifybe.domain.vo.IPAddress

fun interface CreateCardUseCase {

    fun invoke(command: Command): Result

    data class Command(
        val userIp: IPAddress,
        val cardDesignId: Long,
        val season: Season,
        val emotional: Emotional,
        val age: Age,
        val dialect: Dialect,
        val words: List<String>
    )

    sealed interface Result {
        data class Success(val phrase: String) : Result
        data class Failure(val message: String) : Result
    }
}
