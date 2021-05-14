package com.sp.domain

/**
 * @author Jaedoo Lee
 */
class InvalidArgumentException(vararg args: Any) : CommonException(CommonErrorCode.INVALID_TYPE, args)
