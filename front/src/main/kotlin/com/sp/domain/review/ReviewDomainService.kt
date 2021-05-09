package com.sp.domain.review

import com.sp.domain.*
import com.sp.domain.entity.*

/**
 * @author Jaedoo Lee
 */
@DomainService
class ReviewDomainService(
    private val reviewRepository: ReviewRepository
) {
    fun register(params: ReviewRegisterModel): Long {
        return reviewRepository.save(Review.create(params)).no
    }
}
