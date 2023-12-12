package com.beside.greetifybe.application.service

import com.beside.greetifybe.application.port.`in`.CreateCardPhraseUseCase
import com.beside.greetifybe.application.port.out.CardPhraseGeneratorPort
import org.springframework.stereotype.Service

@Service
class CreateCardPhraseService(
    private val cardPhraseGeneratorPort: CardPhraseGeneratorPort,
) : CreateCardPhraseUseCase {

    override fun invoke(command: CreateCardPhraseUseCase.Command): CreateCardPhraseUseCase.Result {
        val phrase: String = cardPhraseGeneratorPort.generate(
            command.season,
            command.emotional,
            command.age,
            command.dialect,
            command.words
        )

        return CreateCardPhraseUseCase.Result.Success(phrase)
    }

}
