package com.beside.greetifybe.adapter.out.redis.config

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
class RedisConfig {

    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.connectionFactory = redisConnectionFactory
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = redisSerializer()
        redisTemplate.setEnableTransactionSupport(true)
        return redisTemplate
    }

    private fun redisSerializer(): RedisSerializer<Any> {
        val polymorphicTypeValidator: BasicPolymorphicTypeValidator = BasicPolymorphicTypeValidator
            .builder()
            .allowIfBaseType(Any::class.java)
            .build()

        val objectMapper: ObjectMapper =
            ObjectMapper()
                .registerModule(kotlinModule())
                .registerModule(JavaTimeModule())
                .activateDefaultTyping(
                    polymorphicTypeValidator,
                    ObjectMapper.DefaultTyping.EVERYTHING,
                    JsonTypeInfo.As.PROPERTY
                )

        return GenericJackson2JsonRedisSerializer(objectMapper)
    }

    private fun kotlinModule(): KotlinModule {
        return KotlinModule.Builder().build()
    }

}
