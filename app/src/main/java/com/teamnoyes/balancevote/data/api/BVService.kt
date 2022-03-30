package com.teamnoyes.balancevote.data.api

import com.teamnoyes.balancevote.data.response.AllVotePostResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BVService {
    //    이후 Pagination 적용 필요. 현재는 Single로 전체 받기
    @GET("/post/vote-post")
    fun getAllVotePost(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20,
        @Query("sortBy") sortBy: String = "writer-asc",
    ): Single<AllVotePostResponse>
}