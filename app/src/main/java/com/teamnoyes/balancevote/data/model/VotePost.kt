package com.teamnoyes.balancevote.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VotePost(
    val id: Long,
    val postId: String,
    val selectionOne: String,
    val selectionTwo: String,
    val uuid: String,
    val voteCntOne: Int,
    val voteCntTwo: Int,
)
