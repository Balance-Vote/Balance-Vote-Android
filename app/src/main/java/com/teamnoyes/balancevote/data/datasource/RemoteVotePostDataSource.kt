package com.teamnoyes.balancevote.data.datasource

import com.teamnoyes.balancevote.data.api.BVService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteVotePostDataSource @Inject constructor(private val api: BVService) {

    fun getMostCommentedPost() = api.getMostCommentedPost()

    fun getMostVotedPost() = api.getMostVotedPost()
}