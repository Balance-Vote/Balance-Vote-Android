package com.teamnoyes.balancevote.presentation.ui.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teamnoyes.balancevote.presentation.ui.theme.BalanceVoteTheme

@Composable
fun SplashScreen() {
    Scaffold {
        SplashBody()
    }
}

@Composable
fun SplashBody(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Surface(
            elevation = 4.dp,
            modifier = modifier.align(Alignment.Center),
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
}

@Preview(name = "SplashScreen Light")
@Composable
fun SplashScreenPreviewLight() {
    SplashScreenPreview(darkTheme = false)
}

@Preview(name = "SplashScreen Dark")
@Composable
fun SplashScreenPreviewDark() {
    SplashScreenPreview(darkTheme = true)
}

@Composable
fun SplashScreenPreview(darkTheme: Boolean) {
    BalanceVoteTheme(darkTheme) {
        SplashScreen()
    }
}