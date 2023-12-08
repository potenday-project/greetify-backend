package com.beside.greetifybe.application.port

import com.beside.greetifybe.domain.Card
import com.beside.greetifybe.domain.vo.IPAddress

interface CardRepository {

    fun save(card: Card)

    fun getByIpAddress(ipAddress: IPAddress): Card?
}
