package com.teamnoyes.balancevote.presentation.ui.screens.post

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teamnoyes.balancevote.presentation.ui.widget.*

@Composable
fun PostScreen(viewModel: PostViewModel = viewModel()) {
    val a = remember { mutableStateOf("") }
    val b = remember { mutableStateOf("") }
    PostScreenBody(onSelectionOneChanged = { a.value = it.text },
        onSelectionTwoChanged = { b.value = it.text }) {
        println(a.value)
        println(b.value)
        viewModel.postNewVote(a.value, b.value, "0419Test")
    }
}

@Composable
fun PostScreenBody(
    onSelectionOneChanged: (TextFieldValue) -> Unit,
    onSelectionTwoChanged: (TextFieldValue) -> Unit,
    onPostVote: () -> Unit
) {
    Box() {
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()) {
            BVInput(
                enableButton = false,
                hintMessage = "1",
                keyboardAction = ImeAction.Next,
                onTextChanged = {onSelectionOneChanged(it)}
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
                onTextChanged = {onSelectionTwoChanged(it)},
                onSendButtonClick = {}
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.BottomCenter)) {
                BVTextButton(
                    text = "POST",
                    onClick = onPostVote,
                    isSelected = false
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPostScreen() {
    PostScreenBody({}, {}, {})
}