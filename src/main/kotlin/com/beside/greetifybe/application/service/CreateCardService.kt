package com.beside.greetifybe.application.service

import com.beside.greetifybe.application.port.`in`.CreateCardUseCase
import com.beside.greetifybe.application.port.out.CardPhraseGenerator
import com.beside.greetifybe.application.port.out.CardQueryHandler
import com.beside.greetifybe.domain.Card
import org.springframework.stereotype.Service

@Service
class CreateCardService(
    private val cardQueryHandler: CardQueryHandler,
    private val cardPhraseGenerator: CardPhraseGenerator,
) : CreateCardUseCase {

    override fun invoke(command: CreateCardUseCase.Command): CreateCardUseCase.Result {
        val phrase: String = cardPhraseGenerator.generate(
            command.season,
            command.emotional,
            command.age,
            command.dialect,
            command.words
        )

        val newCard: Card = Card.create(
            command.userIp,
            command.cardDesignId,
            phrase
        )

        cardQueryHandler.save(newCard)
        return CreateCardUseCase.Result.Success(phrase)
    }

}