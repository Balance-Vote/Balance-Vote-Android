package com.teamnoyes.balancevote.presentation.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.teamnoyes.balancevote.data.model.VotePost
import com.teamnoyes.balancevote.data.repository.VotePostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val votePostRepository: VotePostRepository,
) : ViewModel() {

    val mostVotedPost: Single<VotePost> =
        votePostRepository.getMostVotedPost().map { it.first() }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    val mostCommentedPost: Single<VotePost> =
        votePostRepository.getMostCommentedPost().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getPaging(): Flowable<PagingData<VotePost>> {
        return votePostRepository.paging().cachedIn(viewModelScope)
    }

    companion object {
        const val TAG = "HomeViewModel"
    }
}