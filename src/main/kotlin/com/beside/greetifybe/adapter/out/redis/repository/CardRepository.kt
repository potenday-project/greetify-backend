package com.beside.greetifybe.adapter.out.redis.repository

import com.beside.greetifybe.adapter.out.redis.entity.CardRedisEntity
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
        val cardRedisEntity: CardRedisEntity = CardRedisEntity.from(card)
        redisHandler.setDataExpire(cardRedisEntity.id.toString(), cardRedisEntity, Duration.ofHours(Card.RETENTION_HOUR))
        redisHandler.setDataExpire(cardRedisEntity.userIp, cardRedisEntity.id.toString(), Duration.ofHours(Card.RETENTION_HOUR))
    }

    @Transactional(readOnly = true)
    override fun getByIpAddress(ipAddress: IPAddress): Card? {
        val id: String = redisHandler.getData(ipAddress.value) ?: return null
        val findCardRedisEntity: CardRedisEntity? = redisHandler.getData(id)
        return findCardRedisEntity?.toDomain()
    }

}
