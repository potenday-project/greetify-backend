package com.beside.greetifybe.adapter.`in`.rest

import com.beside.greetifybe.adapter.`in`.rest.dto.*
import com.beside.greetifybe.application.port.`in`.CreateCardPhraseUseCase
import com.beside.greetifybe.application.port.`in`.CreateCardUseCase
import com.beside.greetifybe.application.port.`in`.GetRecentCardUseCase
import com.beside.greetifybe.domain.vo.IPAddress
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CardApiController(
    private val createCardPhraseUseCase: CreateCardPhraseUseCase,
    private val createCardUseCase: CreateCardUseCase,
    private val getRecentCardUseCase: GetRecentCardUseCase,
) : CardApi {

    override fun createCardPhrase(
        @RequestBody
        createCardPhraseRequest: CreateCardPhraseRequest
    ): CreateCardPhraseResponse {
        val useCaseCommand: CreateCardPhraseUseCase.Command = CreateCardPhraseUseCase.Command(
            cardDesignId = createCardPhraseRequest.cardDesignId,
            season = createCardPhraseRequest.season,
            emotional = createCardPhraseRequest.emotional,
            age = createCardPhraseRequest.age,
            dialect = createCardPhraseRequest.dialect,
            words = createCardPhraseRequest.words
        )

        when (val result: CreateCardPhraseUseCase.Result = createCardPhraseUseCase.invoke(useCaseCommand)) {
            is CreateCardPhraseUseCase.Result.Success -> return CreateCardPhraseResponse.from(phrase = result.phrase)
            is CreateCardPhraseUseCase.Result.Failure -> throw result.exception
        }
    }

    override fun createCard(
        request: HttpServletRequest,
        @RequestBody
        createCardRequest: CreateCardRequest
    ): CreateCardResponse {
        val currentIP = IPAddress(request.remoteAddr)

        val useCaseCommand: CreateCardUseCase.Command = CreateCardUseCase.Command(
            userIp = currentIP,
            cardDesignId = createCardRequest.cardDesignId,
            phrase = createCardRequest.phrase,
        )

        return when (val result: CreateCardUseCase.Result = createCardUseCase.invoke(useCaseCommand)) {
            is CreateCardUseCase.Result.Success -> CreateCardResponse.from(card = result.card)
        }
    }

    override fun getRecentCard(request: HttpServletRequest): GetRecentCardResponse {
        val currentIP = IPAddress(request.remoteAddr)

        return when (val result: GetRecentCardUseCase.Result = getRecentCardUseCase.invoke(currentIP)) {
            is GetRecentCardUseCase.Result.Success -> GetRecentCardResponse.from(result.card)
            is GetRecentCardUseCase.Result.Failure -> throw result.exception
        }
    }

}
