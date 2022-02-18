package com.teamnoyes.balancevote.presentation.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teamnoyes.balancevote.ui.theme.BalanceVoteTheme


/**
 * 버튼은 오른쪽 정렬, 남는 공간을 TextField로 채운다.
 * 버튼이 한 계층 위에 있어 먼저 공간을 결정해야 함
 * 버튼은 정사각형이어야 함
 *
 * @param enableButton 버튼 표시 유무
 * @param textFieldValue 입력 문자열 값
 * @param onTextChanged 입력 문자열 변경시 동작
 */
@Composable
fun BVInput(
    enableButton: Boolean,
    textFieldValue: TextFieldValue,
    onTextChanged: (TextFieldValue) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .background(color = Color.Cyan)
                .weight(1f)
        ) {
            BVTextField(textFieldValue, onTextChanged)
        }
        if (enableButton) {
            Spacer(modifier = Modifier.width(16.dp))
            BVButton()
        }
    }
}

@Composable
fun BVTextField(
    textFieldValue: TextFieldValue,
    onTextChanged: (TextFieldValue) -> Unit
) {
    BasicTextField(
        value = textFieldValue,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth(),
    )
}

@Composable
fun BVButton() {
    Button(
        onClick = { /*TODO*/ }, modifier = Modifier
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
            BVInput(true, TextFieldValue("Hello"), {})
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
            BVInput(false, TextFieldValue("Hello"), {})
        }
    }
}