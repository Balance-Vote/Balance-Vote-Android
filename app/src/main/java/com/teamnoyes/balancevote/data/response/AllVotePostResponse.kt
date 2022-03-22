package com.teamnoyes.balancevote.data.response

import com.squareup.moshi.JsonClass
import com.teamnoyes.balancevote.data.model.Pageable
import com.teamnoyes.balancevote.data.model.Sort
import com.teamnoyes.balancevote.data.model.VotePost

@JsonClass(generateAdapter = true)
data class AllVotePostResponse(
    val content: List<VotePost>,
    val pageable: Pageable,
    val totalPages: Int,
    val totalElements: Int,
    val last: Boolean,
    val size: Int,
    val number: Int,
    val sort: Sort,
    val numberOfElements: Int,
    val first: Boolean,
    val empty: Boolean,
)
