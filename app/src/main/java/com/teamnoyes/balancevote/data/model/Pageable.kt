package com.teamnoyes.balancevote.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pageable(
    val sort: Sort,
    val offset: Long,
    val pageNumber: Int,
    val pageSize: Int,
    val paged: Boolean,
    val unpaged: Boolean
)