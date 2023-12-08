package com.beside.greetifybe.adapter.`in`.rest.dto

import com.beside.greetifybe.domain.enums.Age
import com.beside.greetifybe.domain.enums.Dialect
import com.beside.greetifybe.domain.enums.Emotional
import com.beside.greetifybe.domain.enums.Season

data class CreateCardRequest(
    val cardDesignId: Long,
    val season: Season,
    val emotional: Emotional,
    val age: Age,
    val dialect: Dialect,
    val words: List<String>,
)
