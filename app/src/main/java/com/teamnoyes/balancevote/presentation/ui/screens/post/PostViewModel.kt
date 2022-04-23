package com.teamnoyes.balancevote.presentation.ui.screens.post

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.teamnoyes.balancevote.data.model.VotePost
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
    val postNewVoteUiState = mutableStateOf(PostNewVoteUiState())

    fun postNewVote(selectionOne: String, selectionTwo: String, uuid: String) {
        disposable.add(repository.postNewPost(selectionOne, selectionTwo, uuid)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<VotePost>() {
                override fun onSuccess(t: VotePost) {
                    Log.d(TAG, t.toString())
                    postNewVoteUiState.value = PostNewVoteUiState(isPosted = true)
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, e.stackTraceToString())
                    postNewVoteUiState.value = PostNewVoteUiState(throwError = true)
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