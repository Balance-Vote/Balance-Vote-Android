package com.teamnoyes.balancevote.presentation.ui.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

/**
 * 그래프 컴포넌트.
 * 두 항목의 투표수를 받으면 비율을 보여줌 (전체 비율을 320, 공백 40 합쳐서 360)
 * 인자를 넣지 않으면 랜덤한 비율의 그래프를 표시함
 * */
@Composable
fun BVGraph(
    isRandom: Boolean,
    vote1: Int = 0,
    vote2: Int = 0,
) {
//    vote의 source는 viewModel일 것이므로 별도의 상태 복원 처리는 하지 않음
    val confinguration = LocalConfiguration.current
    val screenWidth = confinguration.screenWidthDp
    var voteRed = vote1.toFloat()
    var voteBlue = vote2.toFloat()
//    둘 다 실제 값이 0일 수도 있으니까 값 입력 안 받으면 랜덤 표시는 문제가 있음. 별도의 인자 사용
    if (isRandom) {
//        랜덤 표시. 두 값이 모두 적당한 크기를 가지게 하기 위해 범위 조정
        voteRed = Random.nextInt(20, 299).toFloat()
        voteBlue = 320 - voteRed
    } else {
//        두 값이 모두 0일 경우를 고려해야 함
        if (voteRed == 0F && voteBlue == 0F) {
            voteRed = 1F
            voteBlue = 1F
        } else {
            voteRed = voteRed / (voteRed + voteBlue) * 320
            voteBlue = voteBlue / (voteRed + voteBlue) * 320
        }
    }
//    box로 text와 겹치게 해보자
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(Modifier
            .fillMaxSize()
            .padding(40.dp)) {
            drawIntoCanvas {
                val rect = Rect(Offset.Zero, this.size)
                val paintRed = Paint()
                paintRed.apply {
                    color = Color.Red
                    strokeWidth = screenWidth/8F
                    style = PaintingStyle.Stroke
                    strokeCap = StrokeCap.Round
                }
                val paintBlue = Paint()
                paintBlue.apply {
                    color = Color.Blue
                    strokeWidth = screenWidth/8F
                    style = PaintingStyle.Stroke
                    strokeCap = StrokeCap.Round
                }
                it.drawArc(rect = rect,
                    startAngle = 280F,
                    sweepAngle = voteRed,
                    useCenter = false,
                    paint = paintRed)
                it.drawArc(rect = rect,
                    startAngle = 300F + voteRed,
                    sweepAngle = voteBlue,
                    useCenter = false,
                    paint = paintBlue)
            }
        }
        if(isRandom) {
            Text(text = "?",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 80.sp,
                fontWeight = FontWeight.Bold)
        }
    }
}

@Preview
@Composable
fun PreviewBVGraphNotRandom() {
    Surface(modifier = Modifier
        .height(480.dp)
        .width(480.dp), color = Color.White) {
        BVGraph(false, 180, 100)
    }
}

@Preview
@Composable
fun PreviewBVGraphRandom() {
    Surface(modifier = Modifier
        .height(480.dp)
        .width(480.dp), color = Color.White) {
        BVGraph(true)
    }
}