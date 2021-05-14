package com.sp.domain.review

import com.sp.domain.entity.*
import org.springframework.data.jpa.repository.*

/**
 * @author Jaedoo Lee
 */
interface ReviewRepository : JpaRepository<Review, Long>
