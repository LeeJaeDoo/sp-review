/*
 * © NHN Corp. All rights reserved.
 * NHN Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.sp.domain



/**
 * @author Hyunsu Bae
 */
abstract class TokenException(val errorCode: ErrorCode) : RuntimeException() {

    override val message: String = MessageConverter.getMessage(errorCode.code)
}
