package com.teamnoyes.balancevote.data.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostNewVoteRequest(val selectionOne: String, val selectionTwo: String, val uuid: String)