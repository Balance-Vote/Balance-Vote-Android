package com.teamnoyes.balancevote.presentation.ui.screens.post

import android.util.Log
import androidx.lifecycle.ViewModel
import com.teamnoyes.balancevote.data.repository.VotePostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val repository: VotePostRepository) : ViewModel() {

    private val disposable = CompositeDisposable()

    fun postNewVote(selectionOne: String, selectionTwo: String, uuid: String) {
        disposable.add(repository.postNewPost(selectionOne, selectionTwo, uuid)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<String>() {
                override fun onSuccess(t: String) {
                    println(t)
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, e.stackTraceToString())
                }
            }))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    companion object {
        const val TAG = "PostViewModel"
    }
}