package com.beside.greetifybe.adapter.out.redis.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class RedisHandler(
    private val redisTemplate: RedisTemplate<String, Any>,
) {
    fun <T> getData(key: String): T? {
        val valueOperations: ValueOperations<String, Any> = redisTemplate.opsForValue()
        return valueOperations[key] as? T
    }

    fun getKeys(pattern: String): Set<String> {
        return redisTemplate.keys(pattern)
    }

    fun isExists(key: String): Boolean {
        return redisTemplate.hasKey(key)
    }

    fun <T> setData(key: String, value: T) {
        val valueOperations: ValueOperations<String, Any> = redisTemplate.opsForValue()
        valueOperations[key] = value as Any
    }

    fun <T> setDataExpire(key: String, value: T, duration: Duration) {
        value as Any
        redisTemplate.opsForValue().set(key, value, duration)
    }

    fun deleteData(key: String) {
        redisTemplate.delete(key)
    }
}
