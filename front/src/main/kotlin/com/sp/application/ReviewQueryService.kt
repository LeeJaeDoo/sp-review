package com.sp.application

import com.sp.domain.review.ReviewRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

/**
 * @author Jaedoo Lee
 */

@Service
class ReviewQueryService(
    private val reviewRepository: ReviewRepository
) {
    suspend fun getReviewsByMember(pageable: Pageable, memberNo: Long): List<ReviewSummary> {
        return reviewRepository.findAllByMemberNoOrderByNoDesc(memberNo, pageable).let { review ->
            review.content.map { ReviewSummary.of(it) }
        }
    }

    suspend fun getReviewsByStore(pageable: Pageable, storeNo: Long): List<ReviewSummary> {
        return reviewRepository.findAllByStoreNoOrderByNoDesc(storeNo, pageable).let { review ->
            review.content.map { ReviewSummary.of(it) }
        }
    }
}
