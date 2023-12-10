package com.beside.greetifybe.adapter.`in`.rest.dto

import com.beside.greetifybe.domain.Card
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "감사 카드 생성 응답")
data class CreateCardResponse(
    @Schema(description = "카드 디자인 ID")
    val cardDesignId: Long,
    @Schema(description = "카드 문구")
    val phrase: String,
    @Schema(description = "카드 생성 일시")
    val createdAt: LocalDateTime
) {
    companion object {

        fun from(card: Card): CreateCardResponse {
            return CreateCardResponse(
                cardDesignId = card.cardDesignId,
                phrase = card.phrase,
                createdAt = card.createdAt
            )
        }

    }
}
