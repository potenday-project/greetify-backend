package com.beside.greetifybe.adapter.out.http.ncp.clova.dto

data class NcpClovaCardPhraseGeneratorRequest(
    val text: String,
    val start: String = "",
    val restart: String = "",
    val includeTokens: Boolean = false,
    val topP: Double = 0.8,
    val topK: Int = 0,
    val maxTokens: Int = 60,
    val temperature: Double = 0.5,
    val repeatPenalty: Double = 5.0,
    val stopBefore: List<String> = listOf("###"),
    val includeAiFilters: Boolean = true
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
    val text: String,
    val stopReason: String,
    val inputText: String,
    val inputLength: Int,
    val inputTokens: List<String>,
    val outputText: String,
    val outputLength: Int,
    val outputTokens: List<String>,
    val probs: List<Double>,
    val ok: Boolean,
    val aiFilter: List<AiFilter>
)

data class AiFilter(
    val groupName: String,
    val name: String,
    val score: String
)
