package com.beside.greetifybe.application.port.out

import com.beside.greetifybe.domain.enums.Age
import com.beside.greetifybe.domain.enums.Dialect
import com.beside.greetifybe.domain.enums.Emotional
import com.beside.greetifybe.domain.enums.Season

interface CardPhraseGeneratorPort {

    fun generate(
        season: Season,
        emotional: List<Emotional>,
        age: List<Age>,
        dialect: Dialect,
        word: List<String>
    ): String

}
