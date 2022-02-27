package com.teamnoyes.balancevote.presentation.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teamnoyes.balancevote.presentation.ui.theme.BalanceVoteTheme

@Composable
fun BVAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onNavIconPressed: () -> Unit = {},
    isNavigationOn: Boolean = false
) {
    Surface(
        shape = RectangleShape,
        modifier = modifier,
        elevation = 4.dp
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(MaterialTheme.colors.background)
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isNavigationOn) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = modifier
                        .size(32.dp)
                        .clickable(onClick = onNavIconPressed)
                        .padding(8.dp),
                    tint = MaterialTheme.colors.onSurface
                )
            }

            Text(
                text = title,
                fontSize = 16.sp,
                color = MaterialTheme.colors.onSurface,
                modifier = modifier.padding(8.dp)
            )
        }
    }
}

@Preview(name = "BVAppBar Light")
@Composable
fun BVAppBarPreviewLight() {
    BVAppBarPreview(darkTheme = false)
}

@Preview(name = "BVAppBar Dark")
@Composable
fun BVAppBarPreviewDark() {
    BVAppBarPreview(darkTheme = true)
}

@Composable
fun BVAppBarPreview(darkTheme: Boolean) {
    BalanceVoteTheme(darkTheme) {
        BVAppBar(
            title = "Test",
        )
    }
}