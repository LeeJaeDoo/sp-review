package com.sp.domain.review

/**
 * @author Jaedoo Lee
 */
data class ReviewRegisterModel(
    val title: String,
    val content: String?,
    val imageUrl: String?,
    val memberNo: Long,
    val storeNo: Long,
    val accessible: Boolean,
    val reliable: Boolean,
)
