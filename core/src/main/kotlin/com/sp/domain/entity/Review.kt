package com.sp.domain.entity

import com.sp.domain.review.ReviewRegisterModel
import org.hibernate.annotations.Type
import java.time.LocalDateTime
import javax.persistence.*

/**
 * @author Jaedoo Lee
 */
@Entity
@Table(name = "mart_review")
data class Review (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_no")
    val no: Long,

    @Column(name = "title")
    val title: String,

    @Column(name = "content")
    val content: String? = "",

    @Column(name = "image_url")
    val imageUrl: String? = null,

    @Column(name = "member_no")
    val memberNo: Long = 0,

    @Column(name = "store_no")
    val storeNo: Long = 0,

    @Type(type = "yes_no")
    @Column(name = "store_accessible_yn")
    val accessible: Boolean = true,

    @Type(type = "yes_no")
    @Column(name = "product_reliable_yn")
    val reliable: Boolean = true,

) {

    @Column(name = "register_ymdt")
    val registerYmdt: LocalDateTime = LocalDateTime.now()

    @Column(name = "update_ymdt")
    val updateYmdt: LocalDateTime? = null

    @Type(type = "yes_no")
    @Column(name = "review_delete_yn")
    val deleted: Boolean = false

    companion object {
        fun create(params: ReviewRegisterModel) = Review(
            no = 0,
            title = params.title,
            content = params.content,
            imageUrl = params.imageUrl,
            memberNo = params.memberNo,
            storeNo = params.storeNo,
            accessible = params.accessible,
            reliable = params.reliable
        )
    }
}
