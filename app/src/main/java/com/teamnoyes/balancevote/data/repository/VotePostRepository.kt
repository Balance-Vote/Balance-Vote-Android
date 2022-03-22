package com.teamnoyes.balancevote.data.repository

import com.teamnoyes.balancevote.data.datasource.RemoteVotePostDataSource
import javax.inject.Inject

class VotePostRepository @Inject constructor(
    private val remoteVotePostDataSource: RemoteVotePostDataSource
) {

    fun getAllVotePost() = remoteVotePostDataSource.getAllVotePost()
}