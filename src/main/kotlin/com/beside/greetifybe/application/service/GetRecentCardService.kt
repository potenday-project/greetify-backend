package com.beside.greetifybe.application.service

import com.beside.greetifybe.application.port.`in`.GetRecentCardUseCase
import com.beside.greetifybe.application.port.out.CardQueryHandler
import com.beside.greetifybe.common.exception.ApiExceptionType
import com.beside.greetifybe.common.exception.CustomException
import com.beside.greetifybe.domain.Card
import com.beside.greetifybe.domain.vo.IPAddress
import org.springframework.stereotype.Service

@Service
class GetRecentCardService(
    private val cardQueryHandler: CardQueryHandler,
) : GetRecentCardUseCase {

    override fun invoke(ipAddress: IPAddress): GetRecentCardUseCase.Result {
        val card: Card? = cardQueryHandler.getByIpAddress(ipAddress)

        return if (card != null) {
            GetRecentCardUseCase.Result.Success(card)
        } else {
            val exception = CustomException(type = ApiExceptionType.RESOURCE_NOT_FOUND, message = "최근 카드가 없습니다.")
            GetRecentCardUseCase.Result.Failure(exception)
        }
    }

}
