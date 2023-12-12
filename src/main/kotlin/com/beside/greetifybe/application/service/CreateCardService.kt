package com.beside.greetifybe.application.service

import com.beside.greetifybe.application.port.`in`.CreateCardUseCase
import com.beside.greetifybe.application.port.out.CardCommandHandler
import com.beside.greetifybe.domain.Card
import org.springframework.stereotype.Service

@Service
class CreateCardService(
    private val cardCommandHandler: CardCommandHandler,
) : CreateCardUseCase {

    override fun invoke(command: CreateCardUseCase.Command): CreateCardUseCase.Result {
        val card: Card = Card.create(
            userIp = command.userIp,
            cardDesignId = command.cardDesignId,
            phrase = command.phrase
        )

        cardCommandHandler.save(card)
        return CreateCardUseCase.Result.Success(card = card)
    }
}
