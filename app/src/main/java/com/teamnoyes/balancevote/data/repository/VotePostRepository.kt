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

    fun getAllVotePost() = remoteVotePostDataSource.getAllVotePost()

    fun paging() = Pager(config = PagingConfig(pageSize = 20), pagingSourceFactory = { votePostPagingSource }).flowable
}