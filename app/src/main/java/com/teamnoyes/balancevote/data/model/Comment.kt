package com.teamnoyes.balancevote.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Comment(
    val id: Int,
    val cmtText: String,
    val timeStamp: String,
    val likeCnt: Int,
    val postId: String,
    val uuid: String,
)