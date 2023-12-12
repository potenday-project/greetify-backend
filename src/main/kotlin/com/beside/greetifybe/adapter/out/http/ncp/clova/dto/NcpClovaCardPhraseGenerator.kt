package com.beside.greetifybe.adapter.out.http.ncp.clova.dto

import com.beside.greetifybe.domain.enums.Age
import com.beside.greetifybe.domain.enums.Dialect
import com.beside.greetifybe.domain.enums.Emotional
import com.beside.greetifybe.domain.enums.Season

data class NcpClovaCardPhraseGeneratorRequest(
    val messages: List<Message>,
    val topP: Double = 0.8,
    val topK: Int = 0,
    val maxTokens: Int = 60,
    val temperature: Double = 0.5,
    val repeatPenalty: Double = 5.0,
    val stopBefore: List<String> = listOf(),
    val includeAiFilters: Boolean = true
) {

    companion object {
        fun of(
            season: Season,
            emotional: List<Emotional>,
            age: List<Age>,
            dialect: Dialect,
            words: List<String>,
        ): NcpClovaCardPhraseGeneratorRequest {
            val trainPrompt: String =
                """
                시즌과 감정, 화자의 연령대, 말투, 단어를 입력으로 전달하면 이를 반영한 카드 문구를 생성한다.
                (최소 10자, 최대 60자 이내)
                입력 예시)
                시즌 : 신년 감정 : 공부 연령대 : 40대 말투 : 충청도 사투리 단어 : 고생했어, 즐거운
                출력 예시)
                신년이 밝았슈! 작년 한 해 고생 많았쥬? 올해는 즐거운 일만 가득하길 바라유. 
                같이 공부 열심히 혀서 좋은 결과 얻어 봅시다.
            """.trimIndent()

            val trainMessage = Message(
                role = "system",
                content = trainPrompt
            )

            val targetContent = """
                시즌 : ${season.value}
                감정 : ${emotional.joinToString(", ") { it.value }} }}
                연령대 : ${age.joinToString(", ") { it.value }}
                말투 : ${dialect.value}
                단어 : ${words.joinToString(", ")}
            """.trimIndent()

            val targetMessage = Message(
                role = "user",
                content = targetContent
            )

            return NcpClovaCardPhraseGeneratorRequest(
                messages = listOf(trainMessage, targetMessage)
            )
        }
    }

}

data class Message(
    val role: String,
    val content: String
)

data class NcpClovaCardPhraseGeneratorResponse(
    val status: Status,
    val result: Result
)

data class Status(
    val code: String,
    val message: String
)

data class Result(
    val message: Message,
    val inputLength: Int,
    val outputLength: Int,
    val stopReason: String,
    val aiFilter: List<AiFilter>
)

data class AiFilter(
    val groupName: String,
    val name: String,
    val score: String
)
