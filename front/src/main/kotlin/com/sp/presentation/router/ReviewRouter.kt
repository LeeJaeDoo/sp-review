package com.sp.presentation.router

import com.sp.presentation.handler.ReviewHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

/**
 * @author Jaedoo Lee
 */
@Configuration
class ReviewRouter(
    private val reviewHandler: ReviewHandler
) {
    @Bean
    fun routeReview() : RouterFunction<ServerResponse> {
        return coRouter {
            ("/front/reviews" and headers { "1.0" in it.header("Version") }).nest {
                accept(MediaType.APPLICATION_JSON).nest {
                    POST("{storeNo}", reviewHandler::register)
                    GET("", reviewHandler::getReviewByMember)
                }
            }
        }
    }
}
