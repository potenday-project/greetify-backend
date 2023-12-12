package com.beside.greetifybe.application.port.`in`

import com.beside.greetifybe.common.exception.CustomException
import com.beside.greetifybe.domain.enums.Age
import com.beside.greetifybe.domain.enums.Dialect
import com.beside.greetifybe.domain.enums.Emotional
import com.beside.greetifybe.domain.enums.Season

fun interface CreateCardPhraseUseCase {

    fun invoke(command: Command): Result

    data class Command(
        val cardDesignId: Long,
        val season: Season,
        val emotional: List<Emotional>,
        val age: List<Age>,
        val dialect: Dialect,
        val words: List<String>
    )

    sealed interface Result {
        data class Success(val phrase: String) : Result
        data class Failure(val exception: CustomException) : Result
    }
}
