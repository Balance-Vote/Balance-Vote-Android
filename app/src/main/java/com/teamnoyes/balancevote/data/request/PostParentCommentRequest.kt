package com.teamnoyes.balancevote.data.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostParentCommentRequest(val cmtText: String, val postId: String, val uuid: String)