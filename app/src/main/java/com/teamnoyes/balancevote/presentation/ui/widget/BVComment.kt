package com.teamnoyes.balancevote.presentation.ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.teamnoyes.balancevote.presentation.ui.theme.BalanceVoteTheme
import java.util.*

//임시 데이터 아마 대댓글까지 할려면 상위 베이스 모델 만들어야할 듯
data class TempModelBVComment(
    val uid: Int,
    val author: String,
    val picked: String,
    val time: Date,
    val nestedCommentList: List<TempModelBVComment> = emptyList(),
    val content: String,
    val like: Int,
    var isLiked: Boolean
) {
    fun getTime() = run {
        val elapsedTime = (Date().time - time.time) / 1000

        val second = 0
        val minute = 60
        val hour = minute * 60
        val day = hour * 24
        val week = day * 7
        val month = week * 5
        val year = month * 12

        val text = when (elapsedTime) {
            in second..minute -> {
                "${elapsedTime}초 전"
            }
            in minute..hour -> {
                "${elapsedTime / minute}분 전"
            }
            in hour..day -> {
                "${elapsedTime / hour}시간 전"
            }
            in day..week -> {
                "${elapsedTime / day}일 전"
            }
            in week..month -> {
                "${elapsedTime / week}주 전"
            }
            in month..year -> {
                "${elapsedTime / month}개월 전"
            }
            else -> {
                "${elapsedTime / year}년 전"
            }
        }

        text
    }


}

@Composable
fun BVComment(
    modifier: Modifier = Modifier,
    onLikedPressed: () -> Unit = {},
    onCommentPressed: () -> Unit = {},
    data: TempModelBVComment,
    shape: Shape = RoundedCornerShape(8.dp),
    elevation: Dp = 4.dp,
) {
    val space = 4.dp
    Surface(
        shape = shape,
        elevation = elevation,
        color = Color.White,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(

        ) {
            Column(
                modifier = modifier
                    .weight(9f)
                    .clickable(onClick = onCommentPressed)
            ) {
                Row(
                    modifier = modifier.padding(8.dp, 4.dp)
                ) {
                    Column(
                        modifier = modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = data.author
                        )
                    }
                    Spacer(modifier = modifier.size(space))

                    Column(
                        modifier = modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = data.picked
                        )
                    }
                    Spacer(modifier = modifier.size(space))

                    Column(
                        modifier = modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = data.getTime()
                        )
                    }
                }

                Row(
                    modifier = modifier.padding(8.dp, 4.dp)
                ) {
                    Text(text = data.content)
                }
            }

            Spacer(modifier = Modifier.size(space))

            Column(
                modifier = modifier
                    .weight(1f)
                    .padding(8.dp, 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (data.isLiked) {
                    Icon(
                        modifier = Modifier.clickable(onClick = onLikedPressed),
                        imageVector = Icons.Default.Favorite, contentDescription = null
                    )
                } else {
                    Icon(
                        modifier = Modifier.clickable(onClick = onLikedPressed),
                        imageVector = Icons.Default.FavoriteBorder, contentDescription = null
                    )
                }
                Text(text = data.like.toString())
            }
        }
    }
}

@Preview(name = "BVComment Light")
@Composable
fun BVCommentPreviewLight() {
    BVCommentPreview(darkTheme = false)
}

@Preview(name = "BVComment Dark")
@Composable
fun BVCommentPreviewDark() {
    BVCommentPreview(darkTheme = true)
}

@Composable
fun BVCommentPreview(darkTheme: Boolean) {
    BalanceVoteTheme(darkTheme) {
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