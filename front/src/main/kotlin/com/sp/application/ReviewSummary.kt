package com.sp.application

import com.sp.domain.entity.Review
import java.time.LocalDateTime

/**
 * @author Jaedoo Lee
 */
data class ReviewSummary(
    val reviewNo: Long,
    val title: String,
    val content: String?,
    val imageUrl: String?,
    val memberNo: Long,
    val storeNo: Long,
    val accessible: Boolean,
    val reliable: Boolean,
    val registerYmdt: LocalDateTime,
    val updateYmdt: LocalDateTime?,
) {
    companion object {
        fun of(review: Review) = ReviewSummary(
            reviewNo = review.no,
            title = review.title,
            content = review.content,
            imageUrl = review.imageUrl,
            memberNo = review.memberNo,
            storeNo = review.storeNo,
            accessible = review.accessible,
            reliable = review.reliable,
            registerYmdt = review.registerYmdt,
            updateYmdt = review.updateYmdt
        )
    }
}
