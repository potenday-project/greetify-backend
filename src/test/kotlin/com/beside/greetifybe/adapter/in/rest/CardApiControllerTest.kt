package com.beside.greetifybe.adapter.`in`.rest

import com.beside.greetifybe.adapter.`in`.rest.dto.CreateCardPhraseRequest
import com.beside.greetifybe.adapter.`in`.rest.dto.CreateCardRequest
import com.beside.greetifybe.application.port.`in`.CreateCardPhraseUseCase
import com.beside.greetifybe.application.port.`in`.CreateCardUseCase
import com.beside.greetifybe.application.port.`in`.GetRecentCardUseCase
import com.beside.greetifybe.domain.Card
import com.beside.greetifybe.domain.enums.Age
import com.beside.greetifybe.domain.enums.Dialect
import com.beside.greetifybe.domain.enums.Emotional
import com.beside.greetifybe.domain.enums.Season
import com.beside.greetifybe.domain.vo.IPAddress
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post


@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class CardApiControllerTest(
    @Autowired
    private val mockMvc: MockMvc,
    @Autowired
    private val objectMapper: ObjectMapper,
    @MockkBean
    private val createCardPhraseUseCase: CreateCardPhraseUseCase,
    @MockkBean
    private val createCardUseCase: CreateCardUseCase,
    @MockkBean
    private val getRecentCardUseCase: GetRecentCardUseCase,
) : DescribeSpec({

    describe("POST : /api/cards/create-phrase") {
        it("성공 : 200 OK") {
            // given
            val request = CreateCardPhraseRequest(
                cardDesignId = 1,
                season = Season.BIRTHDAY,
                emotional = listOf(Emotional.GREETING),
                age = listOf(Age.TEENAGER),
                dialect = Dialect.GYEONGSANG,
                words = listOf("안녕", "반가워")
            )

            val expectedPhrase = "안녕하세요 반가워요"

            // when
            every { createCardPhraseUseCase.invoke(any()) } returns CreateCardPhraseUseCase.Result.Success(phrase = expectedPhrase)


            // then
            mockMvc
                .post("/api/cards/create-phrase") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(request)
                }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        jsonPath("$.phrase") { value(expectedPhrase) }
                    }
                }
        }
    }

    describe("POST /api/cards") {
        it("성공 : 201 CREATED") {
            // given
            val request = CreateCardRequest(
                cardDesignId = 1,
                phrase = "안녕하세요"
            )

            val expectedCard = Card.create(
                userIp = IPAddress("192.168.0.1"),
                cardDesignId = 1L,
                phrase = "안녕하세요"
            )

            // when
            every { createCardUseCase.invoke(any()) } returns CreateCardUseCase.Result.Success(expectedCard)

            println(objectMapper.writeValueAsString(request))

            // then
            mockMvc
                .post("/api/cards") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(request)
                }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        jsonPath("$.cardDesignId") { isNumber() }
                        jsonPath("$.phrase") { isString() }
                        jsonPath("$.createdAt") { isString() }
                    }
                }
        }
    }

    describe("GET : /api/cards/recent") {
        it("성공 : 200 OK") {
            // given
            val recentCard = Card.create(
                userIp = IPAddress("192.168.0.1"),
                cardDesignId = 1L,
                phrase = "안녕하세요"
            )

            every { getRecentCardUseCase.invoke(any()) } returns GetRecentCardUseCase.Result.Success(recentCard)

            // when, then
            mockMvc
                .get("/api/cards/recent")
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        jsonPath("$.cardDesignId") { isNumber() }
                        jsonPath("$.phrase") { isString() }
                        jsonPath("$.createdAt") { isString() }
                    }
                }
        }
    }
})
