package com.beside.greetifybe.adapter.out.http.ncp.clova

import com.beside.greetifybe.adapter.out.http.ncp.clova.dto.NcpClovaCardPhraseGeneratorRequest
import com.beside.greetifybe.adapter.out.http.ncp.clova.dto.NcpClovaCardPhraseGeneratorResponse
import com.beside.greetifybe.application.port.out.CardPhraseGeneratorPort
import com.beside.greetifybe.domain.enums.Age
import com.beside.greetifybe.domain.enums.Dialect
import com.beside.greetifybe.domain.enums.Emotional
import com.beside.greetifybe.domain.enums.Season
import org.springframework.stereotype.Component

@Component
class NcpPortClovaCardPhraseGeneratorAdapter(
    private val properties: NcpClovaProperties,
    private val api: NcpClovaStudioApi,
) : CardPhraseGeneratorPort {

    override fun generate(
        season: Season,
        emotional: List<Emotional>,
        age: List<Age>,
        dialect: Dialect,
        word: List<String>
    ): String {
        val request: NcpClovaCardPhraseGeneratorRequest = NcpClovaCardPhraseGeneratorRequest.of(
            season = season,
            emotional = emotional,
            age = age,
            dialect = dialect,
            words = word
        )

        val response: NcpClovaCardPhraseGeneratorResponse =
            api.send(
                clovaStudioApiKey = properties.clovaStudioApiKey,
                apiGatewayKey = properties.apiGatewayKey,
                clovaStudioRequestId = properties.clovaStudioRequestId,
                request = request
            )

        return response.result.message.content
    }
}
