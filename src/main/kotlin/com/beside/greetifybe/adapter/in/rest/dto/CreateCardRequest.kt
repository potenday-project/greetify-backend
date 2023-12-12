package com.beside.greetifybe.adapter.`in`.rest.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "카드 생성 요청")
data class CreateCardRequest(
    @Schema(description = "카드 디자인 ID", example = "1")
    val cardDesignId: Long,
    @Schema(description = "카드 문구", example = "선생님 가르쳐주셔서 감사합니다.")
    val phrase: String,
)
