package com.teamnoyes.balancevote.presentation.ui.screens.vote.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.teamnoyes.balancevote.R
import com.teamnoyes.balancevote.data.model.Comment
import com.teamnoyes.balancevote.data.model.VotePost
import com.teamnoyes.balancevote.presentation.ui.theme.BackGround
import com.teamnoyes.balancevote.presentation.ui.theme.BalanceVoteTheme
import com.teamnoyes.balancevote.presentation.ui.widget.BVComment
import com.teamnoyes.balancevote.presentation.ui.widget.BVGraph
import com.teamnoyes.balancevote.presentation.ui.widget.BVInput
import com.teamnoyes.balancevote.presentation.ui.widget.TempModelBVComment
import java.util.*

@Composable
fun DetailVoteScreen(
    viewModel: DetailVoteViewModel = viewModel(),
    navController: NavController,
    postId: String,
    nickname: MutableState<String>,
) {
    viewModel.getVotePost(postId)
    viewModel.getCommentList(postId)
//    수정 필요. DetailVoteBody만 드러나야함
    Column() {
        DetailVoteBody(modifier = Modifier
            .weight(1f),
            viewModel.votePost.value,
            viewModel.commentList)

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp, 0.dp)
        ) {
            BVInput(
                enableButton = true,
                hintMessage = stringResource(id = R.string.detail_vote_hint),
                onSendButtonClick = { comment ->
                    viewModel.postParentComment(comment, postId, nickname.value)
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailVoteBody(
    modifier: Modifier = Modifier,
    votePost: VotePost,
    commentList: List<Comment>,
) {
    LazyColumn(modifier = modifier) {
        items(count = 1) {
            VoteTitle(modifier = modifier,
                leftTopic = votePost.selectionOne,
                rightTopic = votePost.selectionTwo)
        }
        items(count = 1) {
            VoteBody(modifier = modifier, votePost = votePost)
        }
        items(count = 1) {
            Text(
                modifier = modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                text = stringResource(id = R.string.detail_vote_header_comment),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        items(commentList) { comment ->
            ParentComment(modifier = modifier.padding(horizontal = 24.dp), comment = comment)
        }
    }
}

@Composable
fun VoteTitle(modifier: Modifier, leftTopic: String, rightTopic: String) {
    Row(
        modifier = modifier.padding(horizontal = 24.dp, vertical = 8.dp),
    ) {
        Text(text = leftTopic, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
        Text(
            text = "VS", modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(8.dp, 0.dp)
        )
        Text(text = rightTopic, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
    }
}

@Composable
fun VoteBody(modifier: Modifier, votePost: VotePost) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(360.dp)
            .padding(horizontal = 24.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Surface(
                modifier = Modifier.height(280.dp)
            ) {
                BVGraph(isRandom = false, vote1 = votePost.voteCntTwo, vote2 = votePost.voteCntOne)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val percentOne =
                    votePost.voteCntOne.toFloat() / (votePost.voteCntOne + votePost.voteCntTwo) * 100
                val percentTwo =
                    votePost.voteCntTwo.toFloat() / (votePost.voteCntOne + votePost.voteCntTwo) * 100
                TopicData(modifier = modifier,
                    topic = votePost.selectionOne,
                    percent = percentOne.toInt())
                TopicData(modifier = modifier,
                    topic = votePost.selectionTwo,
                    percent = percentTwo.toInt())
            }
        }
    }

}

@Composable
fun TopicData(modifier: Modifier, topic: String, percent: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = topic, fontSize = 20.sp)
        Text(text = "${percent}%", fontSize = 18.sp)
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ParentComment(modifier: Modifier, comment: Comment) {
    BVComment(
        modifier = modifier,
        data = TempModelBVComment(
            uid = comment.id,
            author = comment.uuid,
            picked = "",
            time = Date(),
            content = comment.cmtText,
            like = comment.likeCnt,
            isLiked = false
        )
    )
//
//    for (i in 0 until 20) {
//        ChildComment(modifier = modifier)
//    }

}

@Composable
fun ChildComment(modifier: Modifier) {
    Row(
        modifier = modifier.padding(12.dp, 0.dp, 0.dp, 0.dp)
    ) {
        BVComment(
            data = TempModelBVComment(
                uid = 0,
                author = "Tester",
                picked = "딱복",
                time = Date(),
                content = "내용\nTEST\nTest\nTEst",
                like = 0,
                isLiked = false
            )
        )
    }
}

@Preview(name = "DetailVoteScreen Light", showBackground = true)
@Composable
fun DetailVoteScreenPreviewLight() {
    DetailVoteScreenPreview(darkTheme = false)
}

@Preview(name = "DetailVoteScreen Dark", showBackground = true, backgroundColor = 0x303030)
@Composable
fun DetailVoteScreenPreviewDark() {
    DetailVoteScreenPreview(darkTheme = true)
}

@Composable
fun DetailVoteScreenPreview(darkTheme: Boolean) {
    BalanceVoteTheme(darkTheme) {
//        DetailVoteScreen(rememberNavController(), "")
    }
}