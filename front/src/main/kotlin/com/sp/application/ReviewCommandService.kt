package com.sp.application

import com.sp.domain.review.*
import org.springframework.stereotype.*

/**
 * @author Jaedoo Lee
 */
@Service
class ReviewCommandService(
    private val reviewDomainService: ReviewDomainService
) {
    suspend fun registerReview(params: ReviewRegisterModel): Long {
        return reviewDomainService.register(params)
    }
}
