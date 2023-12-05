package com.beside.greetifybe.domain

data class Card(
    val cardDesignId: Long,
    val season: Season,
    val emotional: Emotional,
    val age: Age,
    val dialect: Dialect
)
