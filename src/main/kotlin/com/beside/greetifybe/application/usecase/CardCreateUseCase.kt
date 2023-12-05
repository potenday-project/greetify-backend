package com.beside.greetifybe.application.usecase

import com.beside.greetifybe.domain.Age
import com.beside.greetifybe.domain.Dialect
import com.beside.greetifybe.domain.Emotional
import com.beside.greetifybe.domain.Season

interface CardCreateUseCase {

    fun invoke(command: Command): Result

    data class Command(
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
