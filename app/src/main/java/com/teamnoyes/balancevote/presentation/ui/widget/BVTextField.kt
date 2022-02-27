package com.teamnoyes.balancevote.presentation.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teamnoyes.balancevote.presentation.ui.theme.BalanceVoteTheme

/**
 * 버튼은 오른쪽 정렬, 남는 공간을 TextField로 채움
 * TextFieldValue를 remember로 가지고 있어 버튼 클릭시 전송할 수 있도록 함
 * 문자열이 비어있는 경우 전송 버튼이 비활성화 되고, 경고 메시지가 하단에 보여짐
 *
 * @param enableButton 버튼 표시 유무
 * @param onSendButtonClick 전송 버튼 클릭시 동작
 */
@Composable
fun BVInput(
    enableButton: Boolean,
    onSendButtonClick: (String) -> Unit
) {
    var textState by remember { mutableStateOf(TextFieldValue()) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column() {
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
                BVTextField(
                    textFieldValue = textState,
                    onTextChanged = { textState = it },
                    onSendButtonClick = {
                        onSendButtonClick(it.text)
                        textState = TextFieldValue("")
                        focusManager.clearFocus()
                    },
                    focus = focusRequester
                )
            }
            if (enableButton) {
                Spacer(modifier = Modifier.width(16.dp))
                BVButton(textState.text.isEmpty()) {
                    onSendButtonClick(textState.text)
                    textState = TextFieldValue("")
                    focusManager.clearFocus()
                }
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row() {
            Spacer(modifier = Modifier.width(16.dp))
            BVAlertText(isTextEmpty = textState.text.isEmpty())
        }
    }
}

@Composable
fun BVAlertText(isTextEmpty: Boolean) {
    val alertString = if (isTextEmpty) "빈 문자열 경고 - 문자열 리소스" else " "
    Text(text = alertString, color = Color.Red)
}

@Composable
fun BVTextField(
    textFieldValue: TextFieldValue,
    onTextChanged: (TextFieldValue) -> Unit,
    onSendButtonClick: (TextFieldValue) -> Unit,
    focus: FocusRequester
) {
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
                    .focusRequester(focus),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Send
                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        if (textFieldValue.text.isNotEmpty()) {
                            onSendButtonClick(textFieldValue)
                        }
                    }
                )
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
fun BVButton(isTextEmpty: Boolean, onClick: () -> Unit) {
    IconButton(
        onClick = { onClick() },
        modifier = Modifier
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(8.dp))
            .height(64.dp)
            .width(64.dp)
            .background(color = Color.Magenta, shape = RoundedCornerShape(8.dp)),
        enabled = !isTextEmpty
    ) { Icon(Icons.Filled.Send, contentDescription = "Send Button") }
}

@Preview
@Composable
fun PreviewTextFieldWithButton() {
    BalanceVoteTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.background
        ) {
            BVInput(true) {}
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
            BVInput(false) {}
        }
    }
}