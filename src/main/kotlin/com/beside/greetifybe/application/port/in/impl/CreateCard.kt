package com.beside.greetifybe.application.port.`in`.impl

import com.beside.greetifybe.application.port.`in`.CreateCardUseCase
import com.beside.greetifybe.application.port.out.PhraseGenerator
import com.beside.greetifybe.application.port.out.CardRepository
import com.beside.greetifybe.domain.Card
import org.springframework.stereotype.Service

@Service
class CreateCard(
    private val cardRepository: CardRepository,
    private val phraseGenerator: PhraseGenerator,
) : CreateCardUseCase {

    override fun invoke(command: CreateCardUseCase.Command): CreateCardUseCase.Result {
        val phrase: String = phraseGenerator.generatePhrase(
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

        cardRepository.save(newCard)
        return CreateCardUseCase.Result.Success(phrase)
    }

}
