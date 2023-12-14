package com.beside.greetifybe.adapter.out.http.ncp.clova.dto

import com.beside.greetifybe.domain.enums.Age
import com.beside.greetifybe.domain.enums.Dialect
import com.beside.greetifybe.domain.enums.Emotional
import com.beside.greetifybe.domain.enums.Season

data class NcpClovaCardPhraseGeneratorRequest(
    val messages: List<Message>,
    val topP: Double = 0.8,
    val topK: Int = 0,
    val maxTokens: Int = 40,
    val temperature: Double = 0.5,
    val repeatPenalty: Double = 5.0,
    val stopBefore: List<String> = listOf(),
    val includeAiFilters: Boolean = true
) {

    companion object {
        fun of(
            preTrainPrompt: String,
            season: Season,
            emotional: List<Emotional>,
            age: List<Age>,
            dialect: Dialect,
            words: List<String>,
        ): NcpClovaCardPhraseGeneratorRequest {
            val trainMessage = Message(
                role = "system",
                content = preTrainPrompt,
            )

            val targetContent = TargetPrompt(
                season = season,
                emotional = emotional,
                age = age,
                dialect = dialect,
                words = words
            ).toDialogTurn()

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

data class TargetPrompt(
    val season: Season,
    val emotional: List<Emotional>,
    val age: List<Age>,
    val dialect: Dialect,
    val words: List<String>,
) {
    fun toDialogTurn(): String {
        return """
            시즌 : ${season.value}
            감정 : ${emotional.joinToString(", ") { it.value }} }}
            연령대 : ${age.joinToString(", ") { it.value }}
            말투 : ${dialect.value}
            단어 : ${words.joinToString(", ")}
        """.trimIndent()
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
