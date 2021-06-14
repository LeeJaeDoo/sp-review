package com.sp.presentation.handler

import com.sp.application.ReviewCommandService
import com.sp.application.ReviewQueryService
import com.sp.domain.RequestException
import com.sp.presentation.extractMemberInfo
import com.sp.presentation.extractParams
import com.sp.presentation.model.PageInfo
import com.sp.presentation.pathVariableToLong
import com.sp.presentation.request.ReviewRegisterRequest
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.created
import org.springframework.web.reactive.function.server.ServerResponse.ok
import java.net.URI

/**
 * @author Jaedoo Lee
 */
@Component
class ReviewHandler(
    private val reviewCommandService: ReviewCommandService,
    private val reviewQueryService: ReviewQueryService
) {

    suspend fun register(request: ServerRequest): ServerResponse {
        val storeNo = request.pathVariableToLong("storeNo")
        val memberInfo = request.extractMemberInfo()

        val params =
            request.awaitBodyOrNull<ReviewRegisterRequest>()?.toModel(memberInfo.no, storeNo) ?: throw RequestException("body")

        val reviewNo = reviewCommandService.registerReview(params)

        return created(URI.create(reviewNo.toString())).buildAndAwait()

    }

    suspend fun getReviewByMember(request: ServerRequest): ServerResponse {
        val memberInfo = request.extractMemberInfo()
        val params = request.extractParams<PageInfo>()

        return ok().bodyValueAndAwait(reviewQueryService.getReviewsByMember(params, memberInfo.no))

    }

    suspend fun getReviewByStore(request: ServerRequest): ServerResponse {
        val storeNo = request.pathVariableToLong("storeNo")
        val params = request.extractParams<PageInfo>()

        return ok().bodyValueAndAwait(reviewQueryService.getReviewsByStore(params, storeNo))

    }

}
