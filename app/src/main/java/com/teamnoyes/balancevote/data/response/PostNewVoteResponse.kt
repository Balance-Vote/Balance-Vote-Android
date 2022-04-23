package com.teamnoyes.balancevote.data.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostNewVoteResponse(val votePostId: String)