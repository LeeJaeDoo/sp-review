package com.sp.domain

/**
 * @author Jaedoo Lee
 */
enum class CommonErrorCode(override val simpleCode: String) : ErrorCode {
    REQUEST_ERROR("E9001"),
    INVALID_TYPE("E9002")
    ;

    override val code: String
        get() = "error.common.$simpleCode"
}
