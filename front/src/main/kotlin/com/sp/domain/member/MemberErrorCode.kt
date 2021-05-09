/*
 * © NHN Corp. All rights reserved.
 * NHN Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.sp.domain.member

import com.sp.domain.*

/**
 * @author Jaedoo Lee
 */
enum class MemberErrorCode(override val simpleCode: String) : ErrorCode {
    TOKEN_EXPIRED("T0001");

    override val code: String
        get() = "error.member.$simpleCode"
}

