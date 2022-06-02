package com.teamnoyes.balancevote.data.datasource

import com.teamnoyes.balancevote.data.api.BVService
import com.teamnoyes.balancevote.data.request.PostNewVoteRequest
import com.teamnoyes.balancevote.data.request.PostVoteSelection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteVotePostDataSource @Inject constructor(private val api: BVService) {

    fun getMostCommentedPost() = api.getMostCommentedPost()

    fun getMostVotedPost() = api.getMostVotedPost()

    fun postNewVote(selectionOne: String, selectionTwo: String, uuid: String) =
        api.postNewVote(PostNewVoteRequest(selectionOne, selectionTwo, uuid))

    fun postVoteSelection(postId: String, selection: String, uuid: String) =
        api.postVoteSelection(postId, PostVoteSelection(selection, uuid))
}