package com.beside.greetifybe.application.port.out

import com.beside.greetifybe.domain.Card

interface CardCommandHandler {

    fun save(card: Card)
}
