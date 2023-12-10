package com.beside.greetifybe.domain

import com.beside.greetifybe.domain.vo.IPAddress
import com.github.f4b6a3.ulid.UlidCreator
import java.time.LocalDateTime
import java.util.*

data class Card(
    val id: UUID = UlidCreator.getMonotonicUlid().toUuid(),
    val userIp: IPAddress,
    val cardDesignId: Long,
    val phrase: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    companion object {
        const val RETENTION_HOUR: Long = 24

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
