package com.sp.presentation.model

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

/**
 * @author Jaedoo Lee
 */
data class PageInfo(
    private val page: Int = 1,
    private val pageSize: Int = 10
) : Pageable {
    companion object {
        const val MAX_PAGE_SIZE: Int = 10000
    }

    override fun getPageNumber(): Int = page

    override fun hasPrevious(): Boolean = page > 1

    override fun getSort(): Sort = Sort.unsorted()

    override fun next(): Pageable = PageInfo(page + 1, pageSize)

    override fun getPageSize(): Int = pageSize.coerceAtMost(
        MAX_PAGE_SIZE
    )

    override fun getOffset(): Long = getPageSize().toLong() * (page.toLong() - 1L)

    override fun first(): Pageable = PageInfo(1, pageSize)

    override fun previousOrFirst(): Pageable = if (page <= 1) first() else PageInfo(
        page - 1, pageSize
    )
}
