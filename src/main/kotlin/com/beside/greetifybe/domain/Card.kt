package com.beside.greetifybe.domain

import com.beside.greetifybe.domain.enums.Age
import com.beside.greetifybe.domain.enums.Dialect
import com.beside.greetifybe.domain.enums.Emotional
import com.beside.greetifybe.domain.enums.Season
import com.beside.greetifybe.domain.vo.IPAddress
import com.github.f4b6a3.ulid.Ulid
import com.github.f4b6a3.ulid.UlidCreator

data class Card(
    val id: Ulid = UlidCreator.getMonotonicUlid(),
    val userIp: IPAddress,
    val cardDesignId: Long,
    val season: Season,
    val emotional: Emotional,
    val age: Age,
    val dialect: Dialect,
    val phrase: String,
) {
    companion object {
        fun create(
            userIp: IPAddress,
            cardDesignId: Long,
            season: Season,
            emotional: Emotional,
            age: Age,
            dialect: Dialect,
            phrase: String,
        ): Card {
            return Card(
                userIp = userIp,
                cardDesignId = cardDesignId,
                season = season,
                emotional = emotional,
                age = age,
                dialect = dialect,
                phrase = phrase
            )
        }
    }
}
