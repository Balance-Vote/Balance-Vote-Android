package com.teamnoyes.balancevote.presentation.ui.screens.vote

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teamnoyes.balancevote.presentation.ui.theme.BalanceVoteTheme
import com.teamnoyes.balancevote.presentation.ui.widget.BVTextButton
import com.teamnoyes.balancevote.presentation.ui.widget.BVTextButtonRole

@Composable
fun VoteScreen(
    modifier: Modifier = Modifier,
    leftTopic: String = "LEFT",
    rightTopic: String = "RIGHT"
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp, 0.dp)
    ) {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(0.dp, 8.dp)
        ) {
            Text(text = leftTopic, fontSize = 24.sp)
            Text(text = "VS", modifier = modifier
                .align(Alignment.CenterVertically)
                .padding(8.dp, 0.dp))
            Text(text = rightTopic, fontSize = 24.sp)
        }

        Spacer(modifier = modifier.padding(0.dp, 12.dp))
        BVTextButton(
            text = leftTopic,
            onClick = {},
            isSelected = false,
            role = BVTextButtonRole.RED,
            horizontalAlignment = Arrangement.Start,
            height = 100.dp,
            fontSize = 28.sp
        )
        Spacer(modifier = modifier.padding(0.dp, 12.dp))
        BVTextButton(
            text = rightTopic,
            onClick = {},
            isSelected = false,
            role = BVTextButtonRole.BLUE,
            horizontalAlignment = Arrangement.Start,
            height = 100.dp,
            fontSize = 28.sp
        )
    }
}

@Preview(name = "VoteScreen Light")
@Composable
fun VoteScreenPreviewLight() {
    VoteScreenPreview(darkTheme = false)
}

@Preview(name = "VoteScreen Dark")
@Composable
fun VoteScreenPreviewDark() {
    VoteScreenPreview(darkTheme = true)
}

@Composable
fun VoteScreenPreview(darkTheme: Boolean) {
    BalanceVoteTheme(darkTheme) {
        VoteScreen()
    }
}