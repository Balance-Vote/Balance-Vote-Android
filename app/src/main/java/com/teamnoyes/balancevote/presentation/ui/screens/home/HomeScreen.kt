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
    HomeScreenBody(pager = allVotePostList.value, bottomNavPadding = PaddingValues(bottom = 48.dp))
}

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreenBody(pager: Flowable<PagingData<VotePost>>, bottomNavPadding: PaddingValues) {
    val lazyPagingItems = pager.asFlow().collectAsLazyPagingItems()
    Box(modifier = Modifier.padding(bottomNavPadding)) {
        LazyColumn(modifier = Modifier) {
            items(count = 1) {
                val pagerState = rememberPagerState()
                HomeText(text = "Hot Votes")
                HorizontalPager(
                    count = 5,
                    state = pagerState,
                    contentPadding = PaddingValues(horizontal = 32.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    HotVotesItem()
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
fun HotVotesItem() {
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
            Text(text = stringResource(id = R.string.home_card_type))
            Text(text = "Android vs iOS?", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            BVGraph(isRandom = true)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}