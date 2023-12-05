package com.beside.greetifybe.application.usecase

import com.beside.greetifybe.domain.*

interface PhraseCreateUseCase {

    fun invoke(command: Command): Result

    data class Command(val card: Card)

    sealed interface Result {
        data class Success(val phrase: String) : Result
        data class Failure(val message: String) : Result
    }
}
