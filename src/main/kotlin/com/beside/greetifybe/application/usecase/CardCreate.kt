package com.beside.greetifybe.application.usecase

import com.beside.greetifybe.application.port.out.CardRepository
import com.beside.greetifybe.domain.Card
import org.springframework.stereotype.Service

@Service
class CardCreate(
    private val cardRepository: CardRepository,
    private val cardPhraseCreateUseCase: CardPhraseCreateUseCase
) : CardCreateUseCase {

    override fun invoke(command: CardCreateUseCase.Command): CardCreateUseCase.Result {
        val phrase: String = cardPhraseCreateUseCase.invoke(
            CardPhraseCreateUseCase.Command(
                command.season,
                command.emotional,
                command.age,
                command.dialect,
                command.words
            )
        ).let {
            when (it) {
                is CardPhraseCreateUseCase.Result.Success -> it.phrase
                is CardPhraseCreateUseCase.Result.Failure -> return CardCreateUseCase.Result.Failure(it.message)
            }
        }

        val newCard: Card = Card.create(
            command.userIp,
            command.cardDesignId,
            command.season,
            command.emotional,
            command.age,
            command.dialect,
            phrase
        )

        cardRepository.save(newCard)
        return CardCreateUseCase.Result.Success(phrase)
    }

}
