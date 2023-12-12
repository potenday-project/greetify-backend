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
        val prePrompt = getPromptTemplate()

        val data: String = """
            시즌 : ${season.value}\n
            감정 : ${emotional[0].value}, ${emotional[1].value}
            연령대 : ${age[0].value}, ${age[1].value}
            말투 : ${dialect.value}
            단어 1 : ${word[0]}
            단어 2 : ${word[1]}
            """.trimIndent()

        val response: NcpClovaCardPhraseGeneratorResponse =
            api.send(
                clovaStudioApiKey = properties.clovaStudioApiKey,
                apiGatewayKey = properties.apiGatewayKey,
                clovaStudioRequestId = properties.clovaStudioRequestId,
                request = NcpClovaCardPhraseGeneratorRequest(text = prePrompt + data)
            )

        return response.result.outputText.trimIndent()
    }

    private fun getPromptTemplate(): String {
        return """
            시즌과 감정, 화자의 연령대, 말투, 단어1, 단어2 를 반영한 카드 문구를 생성해줘
            
            해당 시즌에 맞는 문구, 감정에 맞는 문구, 연령대에 맞는 문구, 말투에 맞는 문구, 단어1, 단어2를 반영한 문구를 생성해야해
            만약 말투가 사투리라면 반드시 해당 사투리에 맞게 변환해야한다.
            
            \n\n
            예시 1)
            시즌 : 생일\n
            감정 : 친구\n
            연령대 : 10대\n
            말투 : 경상도 사투리\n
            단어1 : 연락\n
            단어2 : 자주\n
            마 생일 축하한다 ㅋㅋ, 연락좀 자주해라 임마\n
            ###\n
            예시 2)
            시즌 : 크리스마스\n
            감정 : 인사\n
            연령대 : 20대\n
            말투 : 셀럽 말투\n
            단어1 : 고생했어\n
            단어2 : 즐거운\n
            메리크리스마스! 여러분 모두 올 한해 정말 고생 많았어요~ 즐거운 크리스마스 보내용 ㅎㅎ\n
            ###\n
            예시 3)
            시즌 : 신년\n
            감정 : 공부\n
            연령대 : 30대\n
            말투 : 제주도 사투리\n
            단어 1 : 합격\n
            단어 2 : 화이팅\n
            올해는 꼭 합격할거우다!! 화이팅허십서예~~\n
            ###\n
            
            입력
            """.trimIndent()
    }
}
