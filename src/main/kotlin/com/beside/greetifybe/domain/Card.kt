package com.beside.greetifybe.domain

import com.beside.greetifybe.domain.enums.Age
import com.beside.greetifybe.domain.enums.Dialect
import com.beside.greetifybe.domain.enums.Emotional
import com.beside.greetifybe.domain.enums.Season

data class Card(
    val cardDesignId: Long,
    val season: Season,
    val emotional: Emotional,
    val age: Age,
    val dialect: Dialect
)
