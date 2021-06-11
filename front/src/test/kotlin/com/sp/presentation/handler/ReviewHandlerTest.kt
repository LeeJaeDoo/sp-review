package com.sp.presentation.handler

import com.sp.application.ReviewCommandService
import com.sp.application.ReviewQueryService
import com.sp.application.ReviewSummary
import com.sp.presentation.MemberInfoConstant
import com.sp.presentation.model.PageInfo
import com.sp.presentation.request.ReviewRegisterRequest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.domain.PageImpl
import org.springframework.http.HttpStatus
import org.springframework.mock.web.reactive.function.server.MockServerRequest
import reactor.core.publisher.Mono
import java.time.LocalDateTime

/**
 * @author Jaedoo Lee
 */
@ExtendWith(MockKExtension::class)
internal class ReviewHandlerTest {

    @MockK
    private lateinit var reviewCommandService: ReviewCommandService

    @MockK
    private lateinit var reviewQueryService: ReviewQueryService

    private lateinit var reviewHandler: ReviewHandler

    @BeforeEach
    fun setUp() {
        reviewHandler = ReviewHandler(reviewCommandService, reviewQueryService)
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

    @Test
    fun `마이페이지 리뷰 조회`() {
        val memberInfo = MemberInfoConstant.testMemberInfo
        val pageInfo = PageInfo(1, 10)

        val request = MockServerRequest.builder()
            .attribute(MemberInfoConstant.ATTRIBUTE_NAME, memberInfo)
            .body(Mono.just(pageInfo))

        val reviews = PageImpl((1L..10L).map {
            ReviewSummary(
                reviewNo = it,
                title = "동행",
                content = "넌 울고 있었고 난 무력했지 슬픔을 보듬기엔 내가 너무 작아서 그런 널 바라보며 내가 할 수 있던 건 함께 울어주기",
                imageUrl = "http://image.url",
                storeNo = it,
                accessible = true,
                reliable = true,
                registerYmdt = LocalDateTime.MAX,
                updateYmdt = null
            )
        }).toList()

        coEvery { reviewQueryService.getReviews(pageInfo, memberInfo.no) } returns reviews

        //when
        val response = runBlocking { reviewHandler.getReviewByMember(request) }
        //then
        assertEquals(HttpStatus.OK, response.statusCode())
        coVerify { reviewQueryService.getReviews(any(), any()) }
    }

}
