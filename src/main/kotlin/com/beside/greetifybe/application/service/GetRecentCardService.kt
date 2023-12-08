package com.beside.greetifybe.application.service

import com.beside.greetifybe.application.port.`in`.GetRecentCardUseCase
import com.beside.greetifybe.application.port.out.CardQueryHandler
import com.beside.greetifybe.domain.vo.IPAddress
import org.springframework.stereotype.Service

@Service
class GetRecentCardService(
    private val cardQueryHandler: CardQueryHandler,
) : GetRecentCardUseCase {

    override fun invoke(ipAddress: IPAddress): GetRecentCardUseCase.Result {
        TODO("Not yet implemented")
    }

}
