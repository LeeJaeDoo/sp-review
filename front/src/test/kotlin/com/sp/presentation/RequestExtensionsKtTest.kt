package com.sp.presentation

import com.sp.domain.*
import com.sp.domain.member.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.springframework.mock.web.reactive.function.server.*

/**
 * @author Jaedoo Lee
 */
internal class RequestExtensionsKtTest {

    @Test
    fun `토큰에서 회원 정보 추출 실패`() {
        val request = MockServerRequest.builder().build()

        assertThrows<MemberTokenInvalidException> {
            request.extractMemberInfo()
        }

    }

    @Test
    fun pathVariableToLong() {
        val storeNo = 1L
        val request = MockServerRequest.builder()
            .pathVariable("storeNo", storeNo.toString())
            .build()

        val result = request.pathVariableToLong("storeNo")

        assertEquals(storeNo, result)
    }

    @Test
    fun pathVaribaleToLong_fail() {
        val storeNo = "store"
        val request = MockServerRequest.builder()
            .pathVariable("storeNo", storeNo)
            .build()

        assertThrows<RequestException> {
            request.pathVariableToLong("storeNo")
        }
    }
}
