package com.beside.greetifybe.adapter.`in`.rest.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "카드 문구 생성 응답")
data class CreateCardPhraseResponse(
    @Schema(description = "카드 문구")
    val phrase: String,
) {
    companion object {

        fun from(phrase: String): CreateCardPhraseResponse {
            return CreateCardPhraseResponse(phrase)
        }

    }
}
