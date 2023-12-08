package com.beside.greetifybe.application.usecase

import com.beside.greetifybe.application.port.out.CardRepository
import com.beside.greetifybe.domain.vo.IPAddress
import org.springframework.stereotype.Service

@Service
class CardGetRecent(
    private val cardRepository: CardRepository,
) : CardGetRecentUseCase {

    override fun invoke(ipAddress: IPAddress): CardGetRecentUseCase.Result {
        TODO("Not yet implemented")
    }

}
