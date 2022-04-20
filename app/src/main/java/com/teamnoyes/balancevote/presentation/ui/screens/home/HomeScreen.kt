package com.teamnoyes.balancevote.presentation.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.teamnoyes.balancevote.R
import com.teamnoyes.balancevote.data.model.VotePost
import com.teamnoyes.balancevote.presentation.ui.widget.BVGraph
import com.teamnoyes.balancevote.presentation.ui.widget.BVTextButton
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.reactive.asFlow

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {
    val allVotePostList = remember { mutableStateOf(homeViewModel.getPaging()) }
//    추후 개선 사항: api를 따로 받아도 좋지만, 한꺼번에 받으면 추후 확장(새로운 항목 추가 등)에 있어 유리할 듯 하다. List로 받아서 각각 생성 등..
    val mostVoted = homeViewModel.mostVotedPost.subscribeAsState(initial = VotePost(
        0L,
        "",
        "",
        "",
        "",
        1,
        1))
    val mostCommented = homeViewModel.mostCommentedPost.subscribeAsState(initial = VotePost(
        0L,
        "",
        "",
        "",
        "",
        1,
        1))
    HomeScreenBody(pager = allVotePostList.value, mostVoted.value, mostCommented.value)
}

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreenBody(
    pager: Flowable<PagingData<VotePost>>,
    mostVoted: VotePost?,
    mostCommented: VotePost?,
) {
    val lazyPagingItems = pager.asFlow().collectAsLazyPagingItems()
    Box() {
        LazyColumn(modifier = Modifier) {
            items(count = 1) {
                val pagerState = rememberPagerState()
                HomeText(text = "Hot Votes")
                HorizontalPager(
                    count = 2,
                    state = pagerState,
                    contentPadding = PaddingValues(horizontal = 32.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    when (this.currentPage) {
                        0 -> {
                            HotVotesItem(mostVoted,
                                stringResource(id = R.string.home_hot_most_voted))
                        }
                        1 -> {
                            HotVotesItem(mostCommented,
                                stringResource(id = R.string.home_hot_most_commented))
                        }
                    }
                }
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                )
            }
            stickyHeader {
                HomeText(text = "New Votes")

            }
            items(lazyPagingItems) { votePost ->
                BVTextButton(text = "${votePost?.id} ${votePost?.selectionOne} vs ${votePost?.selectionTwo}",
                    onClick = {},
                    isSelected = false
                )
            }
        }
    }
}

@Composable
fun HomeText(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun HotVotesItem(votePost: VotePost?, title: String) {
//    가로 길이를 기기의 가로 길이에 기반하도록 하고, 세로 길이는 가로 길이와 1:1로
//    가로 길이에 맞게 그래프의 테두리 굵기 조절도 필요함
    Card(
        modifier = Modifier
            .width(320.dp)
            .height(320.dp)
            .padding(horizontal = 8.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(32.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title)
            Text(text = "${votePost?.selectionOne} vs ${votePost?.selectionTwo}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)
            BVGraph(isRandom = votePost == null,
                vote1 = votePost?.voteCntOne ?: 0,
                vote2 = votePost?.voteCntTwo ?: 0)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}