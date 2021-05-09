package com.sp.presentation.request

import com.sp.domain.review.*

/**
 * @author Jaedoo Lee
 */
data class ReviewRegisterRequest(
    val title: String,
    val content: String,
    val imageUrl: String?,
    val accessible: Boolean,
    val reliable: Boolean,
) {
    fun toModel(memberNo: Long, storeNo: Long) = ReviewRegisterModel(
        title = title,
        content = content,
        imageUrl = imageUrl,
        memberNo = memberNo,
        storeNo = storeNo,
        accessible = accessible,
        reliable = reliable
    )
}
