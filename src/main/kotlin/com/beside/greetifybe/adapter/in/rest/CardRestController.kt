package com.beside.greetifybe.adapter.`in`.rest

import com.beside.greetifybe.adapter.`in`.rest.dto.CreateCardRequest
import com.beside.greetifybe.adapter.`in`.rest.dto.GetRecentCardResponse
import com.beside.greetifybe.application.usecase.CardCreateUseCase
import com.beside.greetifybe.application.usecase.CardGetRecentUseCase
import com.beside.greetifybe.common.exception.NotFoundException
import com.beside.greetifybe.domain.vo.IPAddress
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cards")
class CardRestController(
    private val cardCreateUseCase: CardCreateUseCase,
    private val cardGetRecentUseCase: CardGetRecentUseCase,
) {

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun createCard(
        request: HttpServletRequest,
        @RequestBody
        createCardRequest: CreateCardRequest
    ) {
        val currentIP = IPAddress(request.remoteAddr)
        val useCaseCommand: CardCreateUseCase.Command = CardCreateUseCase.Command(
            userIp = currentIP,
            cardDesignId = createCardRequest.cardDesignId,
            season = createCardRequest.season,
            emotional = createCardRequest.emotional,
            age = createCardRequest.age,
            dialect = createCardRequest.dialect,
            words = createCardRequest.words
        )

        cardCreateUseCase.invoke(useCaseCommand)
    }

    @GetMapping("/recent")
    @ResponseStatus(HttpStatus.OK)
    fun getRecentCard(request: HttpServletRequest): GetRecentCardResponse {
        val currentIP = IPAddress(request.remoteAddr)
        val result: CardGetRecentUseCase.Result = cardGetRecentUseCase.invoke(currentIP)

        return when (result) {
            is CardGetRecentUseCase.Result.Success -> GetRecentCardResponse.from(result.card)
            is CardGetRecentUseCase.Result.Failure -> throw NotFoundException(result.message)
        }
    }

}
