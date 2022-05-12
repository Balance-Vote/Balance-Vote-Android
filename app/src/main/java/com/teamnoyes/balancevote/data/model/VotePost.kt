package com.teamnoyes.balancevote.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VotePost(
    val id: Long = 0L,
    val postId: String = "",
    val selectionOne: String = "",
    val selectionTwo: String = "",
    val uuid: String = "",
    val voteCntOne: Int = 1,
    val voteCntTwo: Int = 1,
)
