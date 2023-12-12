package com.beside.greetifybe.adapter.`in`.rest.dto

import com.beside.greetifybe.domain.Card
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(name = "카드 생성 응답")
data class CreateCardResponse(
    val cardDesignId: Long,
    val phrase: String,
    val createdAt: LocalDateTime,
) {

    companion object {
        fun from(card: Card): CreateCardResponse {
            return CreateCardResponse(
                cardDesignId = card.cardDesignId,
                phrase = card.phrase,
                createdAt = card.createdAt,
            )
        }
    }

}
