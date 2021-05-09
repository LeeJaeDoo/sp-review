package com.sp.domain.review

import com.sp.domain.*

/**
 * @author Jaedoo Lee
 */
enum class ReviewErrorCode(override val simpleCode: String) : ErrorCode {

    INVALID_MEMBER_INFO("RF001"),
    ;

    override val code: String
        get() = "error.review.$simpleCode"
}
