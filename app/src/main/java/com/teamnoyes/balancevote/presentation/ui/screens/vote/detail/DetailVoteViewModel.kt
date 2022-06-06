package com.teamnoyes.balancevote.presentation.ui.screens.vote.detail

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
class DetailVoteViewModel @Inject constructor(private val repository: VotePostRepository) :
    ViewModel() {
    //postman 켜서 각각 확인해보기
    val disposable = CompositeDisposable()
    val votePost = mutableStateOf(VotePost())

    //        특정 VotePost를 요청하고 그 결과를 가지고 있어야 함 - getVotePost - Single 함수로 가지고 있고 State로 상태 표시
    fun getVotePost(postId: String) {
        disposable.add(repository.getVotePost(postId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<VotePost>() {
                override fun onSuccess(t: VotePost) {
                    Log.d(TAG, t.toString())
                    votePost.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, e.stackTraceToString())
                }

            }))
    }

//        댓글을 조회해야 함 - 포스트 댓글 조회 - Observable

//        댓글을 등록해야 함 - 부모 댓글 생성 - POST 후 갱신이 필요함!


    companion object {
        const val TAG = "DetailVoteViewModel"
    }
}