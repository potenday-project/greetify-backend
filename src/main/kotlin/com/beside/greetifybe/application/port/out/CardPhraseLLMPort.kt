package com.beside.greetifybe.application.port.out

import com.beside.greetifybe.domain.Card

interface CardPhraseLLMPort {

    fun generatePhrase(card: Card): String
}
