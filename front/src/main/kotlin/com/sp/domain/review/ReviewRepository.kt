package com.sp.domain.review

import com.sp.domain.entity.Review
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Jaedoo Lee
 */
interface ReviewRepository : JpaRepository<Review, Long> {

    fun findAllByMemberNoOrderByNoDesc(memberNo: Long, pageable: Pageable) : Page<Review>

    fun findAllByStoreNoOrderByNoDesc(storeNo: Long, pageable: Pageable) : Page<Review>

}
