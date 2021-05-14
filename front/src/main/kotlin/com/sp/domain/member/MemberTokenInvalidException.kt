package com.sp.domain.member

import com.sp.domain.*

/**
 * @author Jaedoo Lee
 */
class MemberTokenInvalidException : TokenException(MemberErrorCode.TOKEN_EXPIRED)
