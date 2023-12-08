package com.beside.greetifybe.application.port.`in`

import com.beside.greetifybe.domain.enums.Age
import com.beside.greetifybe.domain.enums.Dialect
import com.beside.greetifybe.domain.enums.Emotional
import com.beside.greetifybe.domain.enums.Season

fun interface CreateCardPhraseUseCase {

    fun invoke(command: Command): Result

    data class Command(
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
