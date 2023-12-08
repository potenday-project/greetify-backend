package com.beside.greetifybe.adapter.out.redis

import com.beside.greetifybe.application.port.CardRepository
import com.beside.greetifybe.domain.Card
import com.beside.greetifybe.domain.vo.IPAddress
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.util.concurrent.TimeUnit

@Repository
class CardRedisAdapter(
    private val redisCardTemplate: RedisTemplate<String, Card>,
    private val redisCardIdTemplate: RedisTemplate<String, String>
) : CardRepository {

    override fun save(card: Card) {
        val cardKey = "${card.userIp.value}:${card.id}"
        val ipKey = "userIp:$card.userIp.value"

        redisCardTemplate
            .opsForValue()
            .set(cardKey, card, Card.RETENTION_HOUR, TimeUnit.HOURS)

        redisCardIdTemplate
            .opsForValue()
            .set(ipKey, card.id.toString(), Card.RETENTION_HOUR, TimeUnit.HOURS)
    }

    override fun getByIpAddress(ipAddress: IPAddress): Card? {
        val ipKey = "userIp:$ipAddress.value"
        val id: String = redisCardIdTemplate
            .opsForValue()
            .get(ipKey) ?: return null

        val cardKey = "$ipAddress:$id"

        return redisCardTemplate
            .opsForValue()
            .get(cardKey)
    }

}
