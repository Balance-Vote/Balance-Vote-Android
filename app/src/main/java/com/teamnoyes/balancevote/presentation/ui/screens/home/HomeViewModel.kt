package com.teamnoyes.balancevote.presentation.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.teamnoyes.balancevote.data.model.VotePost
import com.teamnoyes.balancevote.data.repository.VotePostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val votePostRepository: VotePostRepository,
) : ViewModel() {

    private val disposable = CompositeDisposable()

//    Single은 이후에 Hot Votes에 사용하는 것으로
//    fun getAllVotePost() {
//        disposable.add(votePostRepository.getAllVotePost()
//            .subscribeOn(Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeWith(object : DisposableSingleObserver<AllVotePostResponse>() {
//                override fun onSuccess(t: AllVotePostResponse) {
//                    println(t)
//                }
//
//                override fun onError(e: Throwable) {
//                    Log.d(TAG, e.stackTraceToString())
//                }
//            }))
//    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getPaging(): Flowable<PagingData<VotePost>> {
        return votePostRepository.paging().cachedIn(viewModelScope)
    }


    companion object {
        const val TAG = "HomeViewModel"
    }
}