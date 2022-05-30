package com.teamnoyes.balancevote.data.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostVoteSelection(val selectedPos: String, val uuid: String)
