package com.teamnoyes.balancevote.presentation.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.teamnoyes.balancevote.data.repository.VotePostRepository
import com.teamnoyes.balancevote.data.response.AllVotePostResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val votePostRepository: VotePostRepository,
) : ViewModel() {

    private val disposable = CompositeDisposable()

    fun getAllVotePost() {
        disposable.add(votePostRepository.getAllVotePost()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<AllVotePostResponse>() {
                override fun onSuccess(t: AllVotePostResponse) {
                    println(t)
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, e.stackTraceToString())
                }
            }))
    }

    companion object {
        const val TAG = "HomeViewModel"
    }
}