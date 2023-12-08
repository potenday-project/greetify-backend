package com.beside.greetifybe.adapter.out.client.ncp.clova

import com.beside.greetifybe.application.port.out.CardPhraseGenerator
import com.beside.greetifybe.domain.enums.Age
import com.beside.greetifybe.domain.enums.Dialect
import com.beside.greetifybe.domain.enums.Emotional
import com.beside.greetifybe.domain.enums.Season
import org.springframework.stereotype.Component

@Component
class NcpClovaCardPhraseGenerationClient : CardPhraseGenerator {

    override fun generate(
        season: Season,
        emotional: Emotional,
        age: Age,
        dialect: Dialect,
        word: List<String>
    ): String {
        // TODO: NCP Clova API를 이용하여 카드 문구 생성
        return "test"
    }

}
