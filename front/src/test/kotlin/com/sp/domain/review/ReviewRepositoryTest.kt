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
    fun `회원별_리뷰 조회 테스트(페이지_사이즈1)`() {
        val memberNo = 1L
        val page = 1
        val pageSize = 10
        val pageInfo = PageInfo(page, pageSize)

        val reviews = reviewRepository.findAllByMemberNoOrderByNoDesc(memberNo, pageInfo)

        assertEquals(pageSize, reviews.content.size)
    }

    @Test
    fun `회원별_리뷰 조회 테스트(페이지_사이즈2)`() {
        val memberNo = 2L
        val page = 1
        val pageSize = 10
        val pageInfo = PageInfo(page, pageSize)

        val reviews = reviewRepository.findAllByMemberNoOrderByNoDesc(memberNo, pageInfo)

        assertEquals(1, reviews.content.size)
    }

    @Test
    fun `회원별_리뷰 조회 테스트(정렬)`() {
        val memberNo = 1L
        val page = 1
        val pageSize = 2
        val pageInfo = PageInfo(page, pageSize)

        val reviews = reviewRepository.findAllByMemberNoOrderByNoDesc(memberNo, pageInfo)

        assertTrue(reviews.first().no > reviews.last().no)
    }

    @Test
    fun `상점별_리뷰 조회 테스트(페이지_사이즈1)`() {
        val storeNo = 1L
        val page = 1
        val pageSize = 10
        val pageInfo = PageInfo(page, pageSize)

        val reviews = reviewRepository.findAllByStoreNoOrderByNoDesc(storeNo, pageInfo)

        assertEquals(pageSize, reviews.content.size)
    }

    @Test
    fun `상점별_리뷰 조회 테스트(페이지_사이즈2)`() {
        val storeNo = 1L
        val page = 2
        val pageSize = 10
        val pageInfo = PageInfo(page, pageSize)

        val reviews = reviewRepository.findAllByStoreNoOrderByNoDesc(storeNo, pageInfo)

        assertEquals(2, reviews.content.size)
    }
}
