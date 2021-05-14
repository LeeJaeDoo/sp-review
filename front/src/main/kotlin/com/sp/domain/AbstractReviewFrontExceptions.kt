package com.sp.domain

import com.sp.domain.member.*
import com.sp.domain.review.*
import com.sp.domain.review.ReviewErrorCode.*

/**
 * @author Jaedoo Lee
 */
abstract class AbstractReviewFrontExceptions(
    errorCode: ReviewErrorCode,
    vararg args: Any
) : ReviewFrontException(errorCode, args)

class InvalidMemberInfoException : AbstractReviewFrontExceptions(INVALID_MEMBER_INFO)
