package com.beside.greetifybe.adapter.out.http.ncp.clova

import com.beside.greetifybe.adapter.out.http.ncp.clova.dto.NcpClovaCardPhraseGeneratorRequest
import com.beside.greetifybe.adapter.out.http.ncp.clova.dto.NcpClovaCardPhraseGeneratorResponse
import com.beside.greetifybe.application.port.out.CardPhraseGeneratorPort
import com.beside.greetifybe.common.exception.ApiExceptionType
import com.beside.greetifybe.common.exception.CustomException
import com.beside.greetifybe.domain.enums.Age
import com.beside.greetifybe.domain.enums.Dialect
import com.beside.greetifybe.domain.enums.Emotional
import com.beside.greetifybe.domain.enums.Season
import org.springframework.stereotype.Component
import java.net.URI
import java.nio.file.Files
import java.nio.file.Paths

@Component
class NcpPortClovaCardPhraseGeneratorAdapter(
    private val properties: NcpClovaProperties,
    private val api: NcpClovaStudioApi,
) : CardPhraseGeneratorPort {

    companion object {
        private const val PRE_TRAINED_PROMPT_FILE = "PreTrainPrompt.txt"
    }


    override fun generate(
        season: Season,
        emotional: List<Emotional>,
        age: List<Age>,
        dialect: Dialect,
        word: List<String>
    ): String {
        val preTrainPrompt: String = readPreTrainPromptFromFile()
        val request: NcpClovaCardPhraseGeneratorRequest = NcpClovaCardPhraseGeneratorRequest.of(
            preTrainPrompt = preTrainPrompt,
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

        val content: String = response.result.message.content
        return removeQuotationAndNewLine(content)
    }

    private fun removeQuotationAndNewLine(content: String): String {
        return content
            .replace("\"", "")
            .replace("\n", "")
    }

    private fun readPreTrainPromptFromFile(): String {
        val uri: URI = javaClass.classLoader.getResource(PRE_TRAINED_PROMPT_FILE)?.toURI() ?: throw CustomException(
            ApiExceptionType.RESOURCE_NOT_FOUND,
            "PreTrainPrompt.txt 파일을 찾을 수 없습니다."
        )
        return String(Files.readAllBytes(Paths.get(uri)))
    }
}
