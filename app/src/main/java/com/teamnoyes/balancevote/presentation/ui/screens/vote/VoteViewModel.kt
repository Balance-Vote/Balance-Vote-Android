package com.teamnoyes.balancevote.presentation.ui.screens.vote

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.teamnoyes.balancevote.data.repository.VotePostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class VoteViewModel @Inject constructor(private val repository: VotePostRepository) :
    ViewModel() {

    private val disposable = CompositeDisposable()
    val postVoteSelectionUiState = mutableStateOf(PostVoteSelectionUiState())

    fun postVoteSelection(postId: String, selection: String, uuid: String) {
        disposable.add(repository.postVoteSelection(postId, selection, uuid).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object : DisposableSingleObserver<Boolean>() {
                override fun onSuccess(t: Boolean) {
                    if(t) {
                        Log.d(TAG, "PostVoteSelection Success: $t")
                        postVoteSelectionUiState.value = PostVoteSelectionUiState(isPosted = true)
                    } else {
                        Log.d(TAG, "PostVoteSelection Fail: $t")
                        postVoteSelectionUiState.value = PostVoteSelectionUiState(throwError = true)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, e.stackTraceToString())
                    postVoteSelectionUiState.value = PostVoteSelectionUiState(throwError = true)
                }

            }))
    }

    companion object {
        const val TAG = "VoteViewModel"
    }
}