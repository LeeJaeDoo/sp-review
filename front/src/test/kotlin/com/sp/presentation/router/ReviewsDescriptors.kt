package com.sp.presentation.router

import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath

/**
 * @author Jaedoo Lee
 */
fun toDescriptors(prefix: String = ""): Array<FieldDescriptor> = arrayOf(
    fieldWithPath("${prefix}reviewNo")
        .type(JsonFieldType.NUMBER)
        .description("리뷰 번호"),
    fieldWithPath("${prefix}title")
        .type(JsonFieldType.STRING)
        .description("제목"),
    fieldWithPath("${prefix}content")
        .type(JsonFieldType.STRING)
        .description("내용")
        .optional(),
    fieldWithPath("${prefix}imageUrl")
        .type(JsonFieldType.STRING)
        .description("이미지 링크")
        .optional(),
    fieldWithPath("${prefix}memberNo")
        .type(JsonFieldType.NUMBER)
        .description("회원 번호"),
    fieldWithPath("${prefix}storeNo")
        .type(JsonFieldType.NUMBER)
        .description("상점 번호"),
    fieldWithPath("${prefix}accessible")
        .type(JsonFieldType.BOOLEAN)
        .description("상점 접근성 호감도"),
    fieldWithPath("${prefix}reliable")
        .type(JsonFieldType.BOOLEAN)
        .description("과자 정보 신뢰도"),
    fieldWithPath("${prefix}registerYmdt")
        .type(JsonFieldType.STRING)
        .description("등록 날짜"),
    fieldWithPath("${prefix}updateYmdt")
        .type(JsonFieldType.STRING)
        .description("수정 날짜")
        .optional()
)
