package com.teamnoyes.balancevote.data.api

import com.teamnoyes.balancevote.data.model.VotePost
import com.teamnoyes.balancevote.data.request.PostNewVoteRequest
import com.teamnoyes.balancevote.data.request.PostVoteSelection
import com.teamnoyes.balancevote.data.response.AllVotePostResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BVService {
    //    이후 Pagination 적용 필요. 현재는 Single로 전체 받기
    @GET("/post/vote-post")
    fun getAllVotePost(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20,
        @Query("sort") sort: String = "id,desc",
    ): Single<AllVotePostResponse>

    @GET("/post/most-commented")
    fun getMostCommentedPost(): Single<VotePost>

    @GET("/post/most-voted")
    fun getMostVotedPost(@Query("count") count: Int = 1): Single<List<VotePost>>

    @POST("/post/vote-post")
    fun postNewVote(@Body postNewVoteRequest: PostNewVoteRequest): Single<VotePost>

    @POST("/post/vote-post/{postId}")
    fun postVoteSelection(
        @Query("postId") postId: String,
        @Body postVoteSelection: PostVoteSelection,
    ): Single<Boolean>

    @GET("/post/vote-post/{postId}")
    fun getVotePost(@Query("postId") postId: String): Single<VotePost>
}