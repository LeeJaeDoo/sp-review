package com.sp.domain.review

import com.sp.infrastructure.TestDataSupport
import com.sp.presentation.model.PageInfo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.test.context.jdbc.Sql

/**
 * @author Jaedoo Lee
 */
@Sql("file:../flyway/test/insert_reviews.sql")
internal class ReviewRepositoryTest(
    val reviewRepository: ReviewRepository
) : TestDataSupport() {

    @Test
    fun `리뷰 조회 테스트1`() {
        val memberNo = 1L
        val page = 1
        val pageSize = 10
        val pageInfo = PageInfo(page, pageSize)

        val reviews = reviewRepository.findAllByMemberNoOrderByNoDesc(memberNo, pageInfo)

        assertEquals(pageSize, reviews.content.size)
    }

    @Test
    fun `리뷰 조회 테스트2`() {
        val memberNo = 2L
        val page = 1
        val pageSize = 10
        val pageInfo = PageInfo(page, pageSize)

        val reviews = reviewRepository.findAllByMemberNoOrderByNoDesc(memberNo, pageInfo)

        assertEquals(1, reviews.content.size)
    }

    @Test
    fun `리뷰 조회 테스트3`() {
        val memberNo = 1L
        val page = 1
        val pageSize = 2
        val pageInfo = PageInfo(page, pageSize)

        val reviews = reviewRepository.findAllByMemberNoOrderByNoDesc(memberNo, pageInfo)

        assertTrue(reviews.first().no > reviews.last().no)
    }
}
