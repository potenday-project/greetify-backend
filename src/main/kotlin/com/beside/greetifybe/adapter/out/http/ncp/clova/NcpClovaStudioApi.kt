package com.beside.greetifybe.adapter.out.http.ncp.clova


import com.beside.greetifybe.adapter.out.http.ncp.clova.dto.NcpClovaCardPhraseGeneratorRequest
import com.beside.greetifybe.adapter.out.http.ncp.clova.dto.NcpClovaCardPhraseGeneratorResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader


@FeignClient(
    name = "ncp-clova-card-phrase-generator",
    url = "\${ncp.clova-studio.url}"
)
interface NcpClovaStudioApi {

    @PostMapping
    fun send(
        @RequestHeader(name = "X-NCP-CLOVASTUDIO-API-KEY", required = true)
        clovaStudioApiKey: String,
        @RequestHeader(name = "X-NCP-APIGW-API-KEY", required = true)
        apiGatewayKey: String,
        @RequestHeader(name = "X-NCP-CLOVASTUDIO-REQUEST-ID", required = true)
        clovaStudioRequestId: String,
        @RequestBody
        request: NcpClovaCardPhraseGeneratorRequest
    ): NcpClovaCardPhraseGeneratorResponse
}
