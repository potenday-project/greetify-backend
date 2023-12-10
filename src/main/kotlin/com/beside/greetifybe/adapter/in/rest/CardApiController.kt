package com.beside.greetifybe.adapter.`in`.rest

import com.beside.greetifybe.adapter.`in`.rest.dto.CreateCardRequest
import com.beside.greetifybe.adapter.`in`.rest.dto.CreateCardResponse
import com.beside.greetifybe.adapter.`in`.rest.dto.GetRecentCardResponse
import com.beside.greetifybe.application.port.`in`.CreateCardUseCase
import com.beside.greetifybe.application.port.`in`.GetRecentCardUseCase
import com.beside.greetifybe.domain.vo.IPAddress
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cards")
class CardApiController(
    private val createCardUseCase: CreateCardUseCase,
    private val getRecentCardUseCase: GetRecentCardUseCase,
) : CardApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun createCard(
        request: HttpServletRequest,
        @RequestBody
        createCardRequest: CreateCardRequest
    ): CreateCardResponse {
        val currentIP = IPAddress(request.remoteAddr)
        val useCaseCommand: CreateCardUseCase.Command = CreateCardUseCase.Command(
            userIp = currentIP,
            cardDesignId = createCardRequest.cardDesignId,
            season = createCardRequest.season,
            emotional = createCardRequest.emotional,
            age = createCardRequest.age,
            dialect = createCardRequest.dialect,
            words = createCardRequest.words
        )

        when (val result: CreateCardUseCase.Result = createCardUseCase.invoke(useCaseCommand)) {
            is CreateCardUseCase.Result.Success -> return CreateCardResponse.from(result.card)
            is CreateCardUseCase.Result.Failure -> throw result.exception
        }

    }

    @GetMapping("/recent")
    @ResponseStatus(HttpStatus.OK)
    override fun getRecentCard(request: HttpServletRequest): GetRecentCardResponse {
        val currentIP = IPAddress(request.remoteAddr)

        return when (val result: GetRecentCardUseCase.Result = getRecentCardUseCase.invoke(currentIP)) {
            is GetRecentCardUseCase.Result.Success -> GetRecentCardResponse.from(result.card)
            is GetRecentCardUseCase.Result.Failure -> throw result.exception
        }
    }

}
