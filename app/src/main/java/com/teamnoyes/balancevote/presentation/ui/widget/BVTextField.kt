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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teamnoyes.balancevote.R
import com.teamnoyes.balancevote.presentation.ui.theme.BalanceVoteTheme

/**
 * 버튼은 오른쪽 정렬, 남는 공간을 TextField로 채움
 * TextFieldValue를 remember로 가지고 있어 버튼 클릭시 전송할 수 있도록 함
 * 문자열이 비어있는 경우 전송 버튼이 비활성화 되고, 경고 메시지가 하단에 보여짐
 *
 * @param enableButton 버튼 표시 유무
 * @param hintMessage 입력되지 않고, 포커스가 없을 때 표시되는 회색 문자열
 * @param keyboardAction 키보드 입력 완료시 제시되는 동작
 * @param onSendButtonClick 전송 버튼 클릭시 동작
 */
@Composable
fun BVInput(
    enableButton: Boolean,
    hintMessage: String,
    keyboardAction: ImeAction = ImeAction.Send,
    onTextChanged: (TextFieldValue) -> Unit,
    onSendButtonClick: (String) -> Unit = {},
) {
    var textState by remember { mutableStateOf(TextFieldValue()) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val isFirst = remember { mutableStateOf(true) }

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
                    hintMessage = hintMessage,
                    keyboardAction = keyboardAction,
                    onTextChanged = {
                        textState = it
                        isFirst.value = false
                        onTextChanged(it)
                    },
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
            BVAlertText(isTextEmpty = textState.text.isEmpty(), isFirst = isFirst.value)
        }
    }
}

@Composable
fun BVAlertText(isTextEmpty: Boolean, isFirst: Boolean) {
    val alertString =
        if (isTextEmpty && !isFirst) stringResource(id = R.string.bv_text_field_empty) else " "
    Text(text = alertString, color = Color.Red)
}

@Composable
fun BVTextField(
    textFieldValue: TextFieldValue,
    hintMessage: String,
    keyboardAction: ImeAction,
    onTextChanged: (TextFieldValue) -> Unit,
    onSendButtonClick: (TextFieldValue) -> Unit,
    focus: FocusRequester,
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
                    imeAction = keyboardAction
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
                    text = hintMessage,
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
            BVInput(enableButton = true,
                hintMessage = "Another Hint String Test",
                keyboardAction = ImeAction.Send,
                onTextChanged = {}) {}
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
            BVInput(enableButton = false,
                hintMessage = "힌트 문자열 테스트",
                keyboardAction = ImeAction.Send,
                onTextChanged = {}) {}
        }
    }
}