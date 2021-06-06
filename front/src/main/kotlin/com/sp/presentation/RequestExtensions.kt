package com.sp.presentation

import com.fasterxml.jackson.module.kotlin.convertValue
import com.sp.domain.RequestException
import com.sp.domain.extensions.objectMapper
import com.sp.domain.member.MemberTokenInvalidException
import com.sp.presentation.MemberInfoConstant.ATTRIBUTE_NAME
import org.springframework.web.reactive.function.server.ServerRequest
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.jvmErasure

/**
 * @author Jaedoo Lee
 */
fun ServerRequest.extractMemberInfo() : MemberInfo {
    return attributes()[ATTRIBUTE_NAME] as MemberInfo?
        ?: throw MemberTokenInvalidException()
}

fun ServerRequest.pathVariableToLong(name: String): Long =
    pathVariable(name).toLongOrNull() ?: throw RequestException(name)

inline fun <reified T : Any> ServerRequest.extractParams(): T {
    val map = T::class.memberProperties
        .map { property ->
            val params = queryParams()[property.name]
                ?.map { it.split(",") }
                ?.flatten()
                ?.filter { it.isNotBlank() }

            val value: Any? = property.returnType.let {
                when {
                    it.jvmErasure.isSubclassOf(Set::class) -> params?.toSet()
                    it.jvmErasure.isSubclassOf(List::class) -> params
                    it.jvmErasure.isSubclassOf(Collection::class) -> params
                    else -> params?.firstOrNull()
                }
            }
            property.name to value
        }
        .toMap()
        .filterValues { it != null }

    return objectMapper.convertValue(map)
}
