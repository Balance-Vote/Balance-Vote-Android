package com.teamnoyes.balancevote.presentation.ui.screens.vote.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.teamnoyes.balancevote.R
import com.teamnoyes.balancevote.presentation.ui.theme.BalanceVoteTheme
import com.teamnoyes.balancevote.presentation.ui.widget.BVComment
import com.teamnoyes.balancevote.presentation.ui.widget.BVGraph
import com.teamnoyes.balancevote.presentation.ui.widget.BVInput
import com.teamnoyes.balancevote.presentation.ui.widget.TempModelBVComment
import java.util.*

@Composable
fun DetailVoteScreen(navController: NavController) {
    Column() {
        DetailVoteBody(modifier = Modifier.weight(1f))

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp, 0.dp)
        ) {
            BVInput(
                enableButton = true,
                hintMessage = stringResource(id = R.string.detail_vote_hint),
                onTextChanged = {}
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailVoteBody(
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.padding(12.dp, 0.dp)) {
        items(count = 1) {
            VoteTitle(modifier = modifier, leftTopic = "Left", rightTopic = "Right")
        }
        items(count = 1) {
            VoteBody(modifier = modifier)
        }
        items(count = 1) {
            Text(
                modifier = modifier.padding(0.dp, 12.dp),
                text = stringResource(id = R.string.detail_vote_header_comment),
                fontSize = 24.sp
            )
        }
        items(count = 20) {
            ParentComment(modifier = modifier)
        }
    }
}

@Composable
fun VoteTitle(modifier: Modifier, leftTopic: String, rightTopic: String) {
    Row(
        modifier = modifier.padding(0.dp, 8.dp),
    ) {
        Text(text = leftTopic, fontSize = 24.sp)
        Text(
            text = "VS", modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(8.dp, 0.dp)
        )
        Text(text = rightTopic, fontSize = 24.sp)
    }
}

@Composable
fun VoteBody(modifier: Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(450.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Surface(
                modifier = Modifier
                    .padding(4.dp)
                    .height(360.dp)
            ) {
                BVGraph(isRandom = false, 24, 76)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TopicData(modifier = modifier, topic = "Left", percent = 24)
                TopicData(modifier = modifier, topic = "Right", percent = 76)
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
fun ParentComment(modifier: Modifier) {
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
    
    for (i in 0 until 20) {
        ChildComment(modifier = modifier)
    }

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
        DetailVoteScreen(rememberNavController())
    }
}