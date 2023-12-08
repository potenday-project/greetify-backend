package com.beside.greetifybe.domain

import com.beside.greetifybe.domain.vo.IPAddress
import com.github.f4b6a3.ulid.Ulid
import com.github.f4b6a3.ulid.UlidCreator
import java.time.LocalDateTime

data class Card(
    val id: Ulid = UlidCreator.getMonotonicUlid(),
    val userIp: IPAddress,
    val cardDesignId: Long,
    val phrase: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    companion object {
        fun create(
            userIp: IPAddress,
            cardDesignId: Long,
            phrase: String,
        ): Card {
            return Card(
                userIp = userIp,
                cardDesignId = cardDesignId,
                phrase = phrase
            )
        }
    }
}
