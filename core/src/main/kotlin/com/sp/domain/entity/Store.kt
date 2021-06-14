package com.sp.domain.entity

import com.sp.domain.store.StoreRegisterModel
import java.time.LocalDateTime
import javax.persistence.*

/**
 * @autor jhi
 * @version 1.0
 */
@Entity
@Table(name = "mart_store")
private data class Store(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_no")
    val no: Long,

    @Column(name = "store_name")
    val name: String,

    @Column(name = "map_url")
    val mapUrl: String,
) {
    @Column(name = "register_ymdt")
    val registerYmdt: LocalDateTime = LocalDateTime.now()

    @Column(name = "update_ymdt")
    val updateYmdt: LocalDateTime? = null

    companion object {
        fun create(params: StoreRegisterModel) = Store(
            no = 0,
            name = params.storeName,
            mapUrl = params.mapUrl
        )
    }
}
