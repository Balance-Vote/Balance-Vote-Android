package com.teamnoyes.balancevote.presentation.ui.screens.entry

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teamnoyes.balancevote.R
import com.teamnoyes.balancevote.presentation.ui.theme.BalanceVoteTheme
import com.teamnoyes.balancevote.presentation.ui.widget.BVInput
import com.teamnoyes.balancevote.presentation.ui.widget.BVTextButton

@Composable
fun EntryScreen() {
    Scaffold() {
        EntryBody()
    }
}

@Composable
fun EntryBody(
    modifier: Modifier = Modifier
) {
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
                elevation = 4.dp,
                modifier = modifier
                    .align(Alignment.Center),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(200.dp),
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                )
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
                hintMessage = stringResource(id = R.string.entry_hint_nickname)
            )

            BVTextButton(
                modifier = modifier.align(Alignment.BottomCenter),
                text = stringResource(id = R.string.entry_start),
                onClick = { },
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
        EntryScreen()
    }
}