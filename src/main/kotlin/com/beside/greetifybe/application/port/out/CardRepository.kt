package com.beside.greetifybe.application.port.out

import com.beside.greetifybe.domain.Card
import com.beside.greetifybe.domain.vo.IPAddress

interface CardRepository {

    fun save(card: Card)

    fun findByIpAddress(ipAddress: IPAddress): Card?
}
