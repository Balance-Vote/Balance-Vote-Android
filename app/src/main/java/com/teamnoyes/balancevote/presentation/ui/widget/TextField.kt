package com.teamnoyes.balancevote.presentation.ui.widget

import android.text.Layout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.teamnoyes.balancevote.ui.theme.BalanceVoteTheme


/**
 * 버튼은 오른쪽 정렬, 남는 공간을 TextField로 채운다.
 * 버튼이 한 계층 위에 있어 먼저 공간을 결정해야 함
 * 버튼은 정사각형이어야 함
 * TextFieldValue를 remember로 가지고 있어 버튼 클릭시 전송할 수 있도록 함
 *
 * @param enableButton 버튼 표시 유무
 * @param textFieldValue 입력 문자열 값
 * @param onTextChanged 입력 문자열 변경시 동작
 */
@Composable
fun BVInput(
    enableButton: Boolean,
    onSendButtonClick: (String) -> Unit,
    height: Dp = 64.dp
) {
//    전송시 textState.text
    var textState by remember { mutableStateOf(TextFieldValue()) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            BVTextField(textState) { textState = it }
        }
        if (enableButton) {
            Spacer(modifier = Modifier.width(16.dp))
            BVButton { onSendButtonClick(textState.text) }
        }
    }
}

@Composable
fun BVTextField(
    textFieldValue: TextFieldValue,
    onTextChanged: (TextFieldValue) -> Unit
) {
//    text 저장 위치를 어디에?
    Box(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp
        ) {
            BasicTextField(
                value = textFieldValue,
                onValueChange = { onTextChanged(it) },
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .align(CenterStart)
                    .padding(16.dp)
            )
            if (textFieldValue.text.isEmpty()) {
                Text(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .align(CenterStart)
                        .padding(16.dp),
                    text = "댓글 작성...",
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun BVButton(onClick: () -> Unit) {
    Button(
        onClick = { onClick() }, modifier = Modifier
            .height(64.dp)
            .width(64.dp)
    ) {}
}

@Preview
@Composable
fun PreviewTextFieldWithButton() {
    BalanceVoteTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.background
        ) {
            BVInput(true, {})
        }
    }
}

@Preview
@Composable
fun PreviewTextFieldWithButtonEmptyInput() {
    BalanceVoteTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.background
        ) {
            BVInput(true, {})
        }
    }
}

@Preview
@Composable
fun PreviewTextFieldWithoutButton() {
    BalanceVoteTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.background
        ) {
            BVInput(false, {})
        }
    }
}