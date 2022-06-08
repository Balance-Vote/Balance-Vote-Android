package com.teamnoyes.balancevote.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.rxjava3.flowable
import com.teamnoyes.balancevote.data.datasource.VotePostPagingSource
import com.teamnoyes.balancevote.data.datasource.RemoteVotePostDataSource
import javax.inject.Inject

class VotePostRepository @Inject constructor(
    private val remoteVotePostDataSource: RemoteVotePostDataSource,
    private val votePostPagingSource: VotePostPagingSource,
) {
    fun getMostCommentedPost() = remoteVotePostDataSource.getMostCommentedPost()

    fun getMostVotedPost() = remoteVotePostDataSource.getMostVotedPost()

    fun paging() = Pager(config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { votePostPagingSource }).flowable

    fun postNewPost(selectionOne: String, selectionTwo: String, uuid: String) =
        remoteVotePostDataSource.postNewVote(selectionOne, selectionTwo, uuid)

    fun postVoteSelection(postId: String, selection: String, uuid: String) =
        remoteVotePostDataSource.postVoteSelection(postId, selection, uuid)

    fun getVotePost(postId: String) = remoteVotePostDataSource.getVotePost(postId)

    fun postParentComment(comment: String, postId: String, uuid: String) =
        remoteVotePostDataSource.postParentComment(comment, postId, uuid)
}