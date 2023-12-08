package com.beside.greetifybe.application.port.`in`.impl

import com.beside.greetifybe.application.port.`in`.GetRecentCardUseCase
import com.beside.greetifybe.application.port.out.CardRepository
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
