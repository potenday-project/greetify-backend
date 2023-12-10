package com.beside.greetifybe.adapter.`in`.rest

import com.beside.greetifybe.adapter.`in`.rest.dto.CreateCardRequest
import com.beside.greetifybe.adapter.`in`.rest.dto.GetRecentCardResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest

@Tag(name = "카드", description = "감사 카드 관련 API")
interface CardApi {

    @Operation(summary = "감사 카드 생성 API")
    fun createCard(
        request: HttpServletRequest,
        createCardRequest: CreateCardRequest
    )

    @Operation(summary = "최근 생성 카드 조회 API")
    fun getRecentCard(request: HttpServletRequest): GetRecentCardResponse

}
