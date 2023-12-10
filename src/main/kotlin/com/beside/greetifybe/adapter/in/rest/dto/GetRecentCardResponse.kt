package com.beside.greetifybe.adapter.`in`.rest.dto

import com.beside.greetifybe.domain.Card
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "최근 카드 조회 응답")
data class GetRecentCardResponse(
    @Schema(description = "카드 디자인 ID", example = "1")
    val cardDesignId: Long,

    @Schema(description = "카드 문구", example = "선생님, 올 한해동안 가르쳐 주셔서 감사합니다. 내년에는 더 좋은 모습으로 찾아뵙겠습니다.")
    val phrase: String,

    @Schema(description = "카드 생성 일시", example = "2021-10-10T10:10:10")
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
