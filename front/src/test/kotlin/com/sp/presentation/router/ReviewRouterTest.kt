package com.sp.presentation.router

import com.epages.restdocs.apispec.ResourceDocumentation
import com.epages.restdocs.apispec.ResourceSnippetParameters
import com.epages.restdocs.apispec.SimpleType
import com.ninjasquad.springmockk.MockkBean
import com.sp.application.ReviewCommandService
import com.sp.application.ReviewQueryService
import com.sp.application.ReviewSummary
import com.sp.presentation.FrontApiTestSupportFilterFunction
import com.sp.presentation.MemberInfoConstant
import com.sp.presentation.MemberInfoFilter
import com.sp.presentation.handler.ReviewHandler
import com.sp.presentation.model.PageInfo
import com.sp.presentation.request.ReviewRegisterRequest
import io.mockk.coEvery
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.ApplicationContext
import org.springframework.data.domain.PageImpl
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.LocalDateTime

/**
 * @author Jaedoo Lee
 */
@WebFluxTest
@ExtendWith(RestDocumentationExtension::class)
@ContextConfiguration(classes = [ReviewRouter::class, ReviewHandler::class, MemberInfoFilter::class])
internal class ReviewRouterTest(private val context: ApplicationContext) {

    private lateinit var webTestClient: WebTestClient

    private val TAG = "REVIEW"

    @MockkBean
    private lateinit var reviewCommandService: ReviewCommandService

    @MockkBean
    private  lateinit var reviewQueryService: ReviewQueryService

    @BeforeEach
    fun setup(restDocumentation: RestDocumentationContextProvider) {
        webTestClient = WebTestClient.bindToApplicationContext(context)
            .configureClient()
            .baseUrl("http://localhost:8081")
            .filter(WebTestClientRestDocumentation.documentationConfiguration(restDocumentation))
            .filter(FrontApiTestSupportFilterFunction())
            .build()
    }

    @Test
    fun `리뷰 등록`() {
        val storeNo = 1L
        val request = ReviewRegisterRequest(
            title = "말리꽃",
            content = "지쳐 쓰러지며 되돌아 가는 내 삶이 초라해 보인대도 죽어진 네 모습과 함께 한다면 이제 갈 수 있어..",
            imageUrl = null,
            accessible = true,
            reliable = true
        )

        coEvery { reviewCommandService.registerReview(any()) } returns 1L

        webTestClient.post()
            .uri("/reviews/{storeNo}", storeNo)
            .header("Version", "1.0")
            .header(MemberInfoConstant.ACCESS_TOKEN_HEADER, MemberInfoConstant.TEST_ACCESS_TOKEN)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .expectStatus().isCreated
            .expectHeader().valueEquals(HttpHeaders.LOCATION, storeNo.toString())
            .expectBody().consumeWith(
                WebTestClientRestDocumentation.document(
                    "review-register",
                    ResourceDocumentation.resource(
                        ResourceSnippetParameters.builder()
                            .tag(TAG)
                            .description("상점별 리뷰 등록")
                            .requestHeaders(
                                ResourceDocumentation.headerWithName("Version")
                                    .description("버전"),
                                ResourceDocumentation.headerWithName(MemberInfoConstant.ACCESS_TOKEN_HEADER)
                                    .description("AccessToken")
                            )
                            .requestFields(
                                PayloadDocumentation.fieldWithPath("title")
                                    .description("제목")
                                    .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("content")
                                    .description("내용")
                                    .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("imageUrl")
                                    .description("이미지 링크")
                                    .type(JsonFieldType.STRING)
                                    .optional(),
                                PayloadDocumentation.fieldWithPath("accessible")
                                    .description("상점 접근성 호감도")
                                    .type(JsonFieldType.BOOLEAN),
                                PayloadDocumentation.fieldWithPath("reliable")
                                    .description("과자 정보 신뢰도")
                                    .type(JsonFieldType.BOOLEAN)
                            ).build()
                    )
                )
            )
    }

    @Test
    fun `마이페이지 리뷰 조회`() {
        val pageInfo = PageInfo(1, 10)

        val response = PageImpl((1L..10L).map {
            ReviewSummary(
                reviewNo = it,
                title = "동행",
                content = "넌 울고 있었고 난 무력했지 슬픔을 보듬기엔 내가 너무 작아서 그런 널 바라보며 내가 할 수 있던 건 함께 울어주기",
                imageUrl = "http://image.url",
                storeNo = it,
                accessible = true,
                reliable = true,
                registerYmdt = LocalDateTime.now(),
                updateYmdt = null
            )
        }).toList()

        coEvery { reviewQueryService.getReviews(any(), any()) } returns response

        webTestClient.get()
            .uri("/reviews?page=${pageInfo.pageNumber}&pageSize=${pageInfo.pageSize}")
            .header("Version", "1.0")
            .header(MemberInfoConstant.ACCESS_TOKEN_HEADER, MemberInfoConstant.TEST_ACCESS_TOKEN)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody().consumeWith(
                WebTestClientRestDocumentation.document(
                    "mypage-reviews",
                    ResourceDocumentation.resource(
                        ResourceSnippetParameters.builder()
                            .tag(TAG)
                            .description("마이페이지 리뷰 조회")
                            .requestHeaders(
                                ResourceDocumentation.headerWithName("Version")
                                    .description("버전"),
                                ResourceDocumentation.headerWithName(MemberInfoConstant.ACCESS_TOKEN_HEADER)
                                    .description("AccessToken")
                            ).requestParameters(
                                ResourceDocumentation.parameterWithName("page")
                                    .description("페이지")
                                    .type(SimpleType.NUMBER),
                                ResourceDocumentation.parameterWithName("pageSize")
                                    .description("페이지 크기")
                                    .type(SimpleType.NUMBER)
                            ).responseFields(*toDescriptors("[].")).build()
                    )
                )
            )
    }
}
