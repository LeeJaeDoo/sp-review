package com.sp.presentation.router

import com.epages.restdocs.apispec.*
import com.ninjasquad.springmockk.*
import com.sp.application.*
import com.sp.presentation.*
import com.sp.presentation.handler.*
import com.sp.presentation.request.*
import io.mockk.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.*
import org.springframework.boot.test.autoconfigure.web.reactive.*
import org.springframework.context.*
import org.springframework.http.*
import org.springframework.restdocs.*
import org.springframework.restdocs.payload.*
import org.springframework.restdocs.webtestclient.*
import org.springframework.test.context.*
import org.springframework.test.web.reactive.server.*

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
            .uri("/reviews/stores/{storeNo}", storeNo)
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
}
