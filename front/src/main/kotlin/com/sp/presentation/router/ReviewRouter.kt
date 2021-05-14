package com.sp.presentation.router

import com.sp.presentation.handler.*
import org.springframework.context.annotation.*
import org.springframework.http.*
import org.springframework.web.reactive.function.server.*

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
            ("/backend/reviews/stores/{storeNo}" and headers { "1.0" in it.header("Version") }).nest {
                accept(MediaType.APPLICATION_JSON).nest {
                    POST("", reviewHandler::register)
                }
            }
        }
    }
}
