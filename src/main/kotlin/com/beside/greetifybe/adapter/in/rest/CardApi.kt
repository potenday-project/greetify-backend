package com.beside.greetifybe.adapter.`in`.rest

import com.beside.greetifybe.adapter.`in`.rest.dto.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus

@Tag(name = "카드", description = "감사 카드 관련 API")
@RequestMapping("/api/cards")
interface CardApi {

    @Operation(summary = "감사 카드 문구 생성 API")
    @PostMapping("/create-phrase")
    @ResponseStatus(HttpStatus.OK)
    fun createCardPhrase(createCardPhraseRequest: CreateCardPhraseRequest): CreateCardPhraseResponse

    @Operation(summary = "감사 카드 생성 API")
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun createCard(
        request: HttpServletRequest,
        createCardRequest: CreateCardRequest,
    ): CreateCardResponse

    @Operation(summary = "최근 생성 카드 조회 API")
    @GetMapping("/recent")
    @ResponseStatus(HttpStatus.OK)
    fun getRecentCard(request: HttpServletRequest): GetRecentCardResponse

}
