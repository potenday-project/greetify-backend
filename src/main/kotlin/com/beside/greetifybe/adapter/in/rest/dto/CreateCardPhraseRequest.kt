package com.beside.greetifybe.adapter.`in`.rest.dto

import com.beside.greetifybe.domain.enums.Age
import com.beside.greetifybe.domain.enums.Dialect
import com.beside.greetifybe.domain.enums.Emotional
import com.beside.greetifybe.domain.enums.Season
import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "카드 문구 생성 요청")
data class CreateCardPhraseRequest(
    @Schema(description = "카드 디자인 ID", example = "1")
    val cardDesignId: Long,
    @Schema(description = "시즌", example = "CHRISTMAS")
    val season: Season,
    @Schema(description = "감정", example = "[\"GREETING\", \"LOVE\"]")
    val emotional: List<Emotional>,
    @Schema(description = "연령대", example = "[\"TEENAGER\", \"TWENTIES\"]")
    val age: List<Age>,
    @Schema(description = "말투", example = "GYEONGSANG")
    val dialect: Dialect,
    @Schema(description = "단어 리스트", example = "[\"선생님\", \"가르쳐주셔서\"]")
    val words: List<String>,
)
