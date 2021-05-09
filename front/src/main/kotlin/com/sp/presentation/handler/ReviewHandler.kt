package com.sp.presentation.handler

import com.sp.application.*
import com.sp.domain.*
import com.sp.presentation.*
import com.sp.presentation.request.*
import org.springframework.stereotype.*
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.*
import java.net.*

/**
 * @author Jaedoo Lee
 */
@Component
class ReviewHandler(
    private val reviewCommandService: ReviewCommandService
) {

    suspend fun register(request: ServerRequest): ServerResponse {
        val storeNo = request.pathVariableToLong("storeNo")
        val memberInfo = request.extractMemberInfo()

        val params =
            request.awaitBodyOrNull<ReviewRegisterRequest>()?.toModel(memberInfo.no, storeNo) ?: throw RequestException("body")

        val reviewNo = reviewCommandService.registerReview(params)

        return created(URI.create(reviewNo.toString())).buildAndAwait()

    }

}
