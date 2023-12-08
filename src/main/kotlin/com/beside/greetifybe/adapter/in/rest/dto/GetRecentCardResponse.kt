package com.beside.greetifybe.adapter.`in`.rest.dto

import com.beside.greetifybe.domain.Card
import java.time.LocalDateTime

data class GetRecentCardResponse(
    val cardDesignId: Long,
    val phrase: String,
    val createdAt: LocalDateTime,
) {
    companion object {
        fun from(card: Card): GetRecentCardResponse {
            return GetRecentCardResponse(
                cardDesignId = card.cardDesignId,
                phrase = card.phrase,
                createdAt = card.createdAt,
            )
        }
    }
}
