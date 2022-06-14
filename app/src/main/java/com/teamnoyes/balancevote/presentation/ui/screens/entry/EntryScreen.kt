package com.teamnoyes.balancevote.presentation.ui.screens.entry

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.teamnoyes.balancevote.R
import com.teamnoyes.balancevote.presentation.ui.theme.BackGround
import com.teamnoyes.balancevote.presentation.ui.theme.BalanceVoteTheme
import com.teamnoyes.balancevote.presentation.ui.widget.BVInput
import com.teamnoyes.balancevote.presentation.ui.widget.BVTextButton

@Composable
fun EntryScreen(navController: NavController, onSubmit: (String) -> Unit) {
    Scaffold() {
        EntryScreenBody(onClick = { nickname ->
//            nickname입력
            onSubmit(nickname)
            navController.navigate("main") {
                popUpTo("entry") {
                    inclusive = true
                }
            }
        })
    }
}

@Composable
fun EntryScreenBody(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
) {
    var nickname = ""
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(0.dp, 56.dp)
    ) {
        Box(
            modifier = modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
        ) {
            Surface(
                modifier = modifier
                    .width(256.dp)
                    .height(256.dp)
                    .align(Alignment.Center),
            ) {
                Image(painter = painterResource(id = R.drawable.ic_bvlogo),
                    contentDescription = "logo", modifier = Modifier.background(BackGround))
            }
        }
        Box(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(12.dp, 0.dp)
        ) {
            BVInput(
                enableButton = false,
                hintMessage = stringResource(id = R.string.entry_hint_nickname),
                onTextChanged = { nickname = it.text }
            )

            BVTextButton(
                modifier = modifier.align(Alignment.BottomCenter),
                text = stringResource(id = R.string.entry_start),
                onClick = { onClick(nickname) },
                isSelected = false,
                height = 112.dp,
                fontSize = 36.sp
            )
        }
    }

}

@Preview(name = "EntryScreen Light")
@Composable
fun EntryScreenPreviewLight() {
    EntryScreenPreview(darkTheme = false)
}

@Preview(name = "EntryScreen Dark")
@Composable
fun EntryScreenPreviewDark() {
    EntryScreenPreview(darkTheme = true)
}

@Composable
fun EntryScreenPreview(darkTheme: Boolean) {
    BalanceVoteTheme(darkTheme) {
//        EntryScreen()
    }
}