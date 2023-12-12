package com.beside.greetifybe.adapter.`in`.rest.dto

import com.beside.greetifybe.domain.Card
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(name = "카드 생성 응답")
data class CreateCardResponse(
    @Schema(description = "카드 디자인 ID", example = "1")
    val cardDesignId: Long,
    @Schema(description = "카드 문구", example = "선생님 가르쳐주셔서 감사합니다.")
    val phrase: String,
    @Schema(description = "카드 생성 일시", example = "2021-10-10T10:10:10")
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
