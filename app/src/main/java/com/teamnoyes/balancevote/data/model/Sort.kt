package com.teamnoyes.balancevote.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sort(val empty: Boolean, val sorted: Boolean, val unsorted: Boolean)
