package com.beside.greetifybe.application.usecase

import com.beside.greetifybe.application.port.CardRepository
import com.beside.greetifybe.domain.vo.IPAddress
import org.springframework.stereotype.Service

@Service
class GetRecentCard(
    private val cardRepository: CardRepository,
) : GetRecentCardUseCase {

    override fun invoke(ipAddress: IPAddress): GetRecentCardUseCase.Result {
        TODO("Not yet implemented")
    }

}
