package com.beside.greetifybe.application.port.out

import com.beside.greetifybe.domain.Card
import com.beside.greetifybe.domain.vo.IPAddress

interface CardQueryHandler {

    fun save(card: Card)

    fun getByIpAddress(ipAddress: IPAddress): Card?
}
