package com.beside.greetifybe.application.usecase

import com.beside.greetifybe.domain.enums.Age
import com.beside.greetifybe.domain.enums.Dialect
import com.beside.greetifybe.domain.enums.Emotional
import com.beside.greetifybe.domain.enums.Season

interface CardPhraseCreateUseCase {

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
