package com.sp.presentation.handler

import com.sp.application.*
import com.sp.presentation.*
import com.sp.presentation.request.*
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.*
import kotlinx.coroutines.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.*
import org.springframework.http.*
import org.springframework.mock.web.reactive.function.server.*
import reactor.core.publisher.*

/**
 * @author Jaedoo Lee
 */
@ExtendWith(MockKExtension::class)
internal class ReviewHandlerTest {

    @MockK
    private lateinit var reviewCommandService: ReviewCommandService

    private lateinit var reviewHandler: ReviewHandler

    @BeforeEach
    fun setUp() {
        reviewHandler = ReviewHandler(reviewCommandService)
    }

    @Test
    fun `리뷰 등록`() {
        val storeNo = 1L
        val memberInfo = MemberInfoConstant.testMemberInfo
        val requestBody = ReviewRegisterRequest(
            title = "동행",
            content = "네 앞에 놓여 진 세상의 짐을 대신 다 짊어질 수 없을지는 몰라도 둘이서 함께라면 나눌 수가 있을까",
            imageUrl = null,
            accessible = true,
            reliable = true
        )

        val request = MockServerRequest.builder()
            .pathVariable("storeNo", storeNo.toString())
            .attribute(MemberInfoConstant.ATTRIBUTE_NAME, memberInfo)
            .body(Mono.just(requestBody))

        coEvery { reviewCommandService.registerReview(any()) } returns 1L

        //when
        val response = runBlocking { reviewHandler.register(request) }
        //then
        assertEquals(HttpStatus.CREATED, response.statusCode())
        coVerify { reviewCommandService.registerReview(any()) }

    }

}
