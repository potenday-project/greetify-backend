package com.beside.greetifybe.adapter.`in`.rest

import com.beside.greetifybe.adapter.`in`.rest.dto.CreateCardRequest
import com.beside.greetifybe.adapter.`in`.rest.dto.GetRecentCardResponse
import com.beside.greetifybe.application.port.`in`.CreateCardUseCase
import com.beside.greetifybe.application.port.`in`.GetRecentCardUseCase
import com.beside.greetifybe.common.exception.NotFoundException
import com.beside.greetifybe.domain.vo.IPAddress
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cards")
class CardRestController(
    private val createCardUseCase: CreateCardUseCase,
    private val getRecentCardUseCase: GetRecentCardUseCase,
) {

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun createCard(
        request: HttpServletRequest,
        @RequestBody
        createCardRequest: CreateCardRequest
    ) {
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

        createCardUseCase.invoke(useCaseCommand)
    }

    @GetMapping("/recent")
    @ResponseStatus(HttpStatus.OK)
    fun getRecentCard(request: HttpServletRequest): GetRecentCardResponse {
        val currentIP = IPAddress(request.remoteAddr)
        val result: GetRecentCardUseCase.Result = getRecentCardUseCase.invoke(currentIP)

        return when (result) {
            is GetRecentCardUseCase.Result.Success -> GetRecentCardResponse.from(result.card)
            is GetRecentCardUseCase.Result.Failure -> throw NotFoundException(result.message)
        }
    }

}
