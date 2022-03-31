package com.teamnoyes.balancevote.presentation.ui.screens.post

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teamnoyes.balancevote.presentation.ui.widget.*

@Composable
fun PostScreen() {
    Box() {
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()) {
            BVInput(
                enableButton = false,
                hintMessage = "1",
                keyboardAction = ImeAction.Next
            )
            Text(
                text = "VS",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
                    .padding(16.dp),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            BVInput(
                enableButton = false,
                hintMessage = "2",
                keyboardAction = ImeAction.Send,
                onSendButtonClick = {}
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.BottomCenter)) {
                BVTextButton(
                    text = "POST",
                    onClick = {},
                    isSelected = false
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPostScreen() {
    PostScreen()
}