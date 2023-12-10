package com.beside.greetifybe.adapter.out.redis.repository

import com.beside.greetifybe.application.port.out.CardCommandHandler
import com.beside.greetifybe.application.port.out.CardQueryHandler
import com.beside.greetifybe.domain.Card
import com.beside.greetifybe.domain.vo.IPAddress
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.Duration

@Repository
class CardRepository(
    private val redisHandler: RedisHandler,
) : CardQueryHandler, CardCommandHandler {

    @Transactional
    override fun save(card: Card) {
        val cardKey = "${card.userIp.value}:${card.id}"
        val ipKey = "userIp:$card.userIp.value"

        redisHandler.setDataExpire(cardKey, card, Duration.ofHours(Card.RETENTION_HOUR))
        redisHandler.setDataExpire(ipKey, card.id.toString(), Duration.ofHours(Card.RETENTION_HOUR))
    }

    @Transactional(readOnly = true)
    override fun getByIpAddress(ipAddress: IPAddress): Card? {
        val ipKey = "userIp:$ipAddress.value"
        val id: String = redisHandler.getData(ipKey) ?: return null
        val cardKey = "$ipAddress:$id"
        return redisHandler.getData(cardKey)
    }

}
