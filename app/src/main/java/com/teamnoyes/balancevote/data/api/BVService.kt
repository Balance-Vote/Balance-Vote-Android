package com.teamnoyes.balancevote.data.api

import com.teamnoyes.balancevote.data.model.Comment
import com.teamnoyes.balancevote.data.model.VotePost
import com.teamnoyes.balancevote.data.request.PostNewVoteRequest
import com.teamnoyes.balancevote.data.request.PostParentCommentRequest
import com.teamnoyes.balancevote.data.request.PostVoteSelection
import com.teamnoyes.balancevote.data.response.AllVotePostResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

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

    @GET("/comment/get-comment/parent/post-id/{postId}")
    fun getCommentList(@Path("postId") postId: String): Single<List<Comment>>

    @POST("/comment/create-comment/parent")
    fun postParentComment(@Body postParentCommentRequest: PostParentCommentRequest): Single<Comment>
}