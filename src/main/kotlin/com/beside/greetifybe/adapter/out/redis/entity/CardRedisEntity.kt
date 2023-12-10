package com.beside.greetifybe.adapter.out.redis.entity

import com.beside.greetifybe.domain.Card
import com.beside.greetifybe.domain.vo.IPAddress
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*


data class CardRedisEntity(
    val id: UUID,
    val userIp: String,
    val cardDesignId: Long,
    val phrase: String,
    val createdAt: String,
) : Serializable {

    fun toDomain(): Card {
        return Card(
            id = id,
            userIp = IPAddress(userIp),
            cardDesignId = cardDesignId,
            phrase = phrase,
            createdAt = LocalDateTime.parse(createdAt),
        )
    }

    companion object {
        fun from(card: Card): CardRedisEntity {
            return CardRedisEntity(
                id = card.id,
                userIp = card.userIp.value,
                cardDesignId = card.cardDesignId,
                phrase = card.phrase,
                createdAt = card.createdAt.toString(),
            )
        }
    }

}
