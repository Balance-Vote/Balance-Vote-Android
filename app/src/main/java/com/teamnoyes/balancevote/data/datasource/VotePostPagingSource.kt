package com.teamnoyes.balancevote.data.datasource

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.teamnoyes.balancevote.data.api.BVService
import com.teamnoyes.balancevote.data.model.VotePost
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VotePostPagingSource @Inject constructor(private val api: BVService) :
    RxPagingSource<Int, VotePost>() {
    override fun getRefreshKey(state: PagingState<Int, VotePost>): Int? {
//        수정 필요
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        val prevKey = anchorPage.prevKey
        if (prevKey != null) return prevKey + 1
        val nextKey = anchorPage.nextKey
        if (nextKey != null) return nextKey - 1
        return null
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, VotePost>> {
//        이 값이 계속 null이 나와서 같은 값이 반복됨
        val nextPageNumber = params.key ?: 0

        return api.getAllVotePost(params.key ?: 0, 10, "writer-asc").subscribeOn(Schedulers.io())
            .map { result -> LoadResult.Page(data = result.content, null, nextPageNumber+1) }
    }

}