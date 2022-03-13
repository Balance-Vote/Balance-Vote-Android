package com.teamnoyes.balancevote.presentation.ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teamnoyes.balancevote.presentation.ui.theme.BalanceVoteTheme
import com.teamnoyes.balancevote.presentation.ui.theme.BlueChoice
import com.teamnoyes.balancevote.presentation.ui.theme.RedChoice

enum class BVTextButtonRole() {
    NORMAL, RED, BLUE
}

@Composable
fun BVTextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    isSelected: Boolean,
    role: BVTextButtonRole = BVTextButtonRole.NORMAL,
    height: Dp = 64.dp,
    fontSize: TextUnit = 24.sp,
    horizontalAlignment: Arrangement.Horizontal = Arrangement.Center,
    verticalAlign: Alignment.Vertical = Alignment.CenterVertically,
    shape: Shape = RoundedCornerShape(8.dp),
    elevation: Dp = 4.dp
) {
    Surface(
        shape = shape,
        color = if (isSelected) {
            when (role) {
                BVTextButtonRole.NORMAL -> {
                    MaterialTheme.colors.background
                }
                BVTextButtonRole.RED -> {
                    RedChoice
                }
                BVTextButtonRole.BLUE -> {
                    BlueChoice
                }
            }
        } else {
            MaterialTheme.colors.background
        },
        modifier = modifier
            .fillMaxWidth()
            .height(height = height),
        elevation = elevation,
    ) {
        Row(
            horizontalArrangement = horizontalAlignment,
            verticalAlignment = verticalAlign,
            modifier = Modifier.clickable(onClick = onClick)
        ) {
            Text(
                text = text,
                fontSize = fontSize,
                color = MaterialTheme.colors.onSurface,
                modifier = modifier.padding(8.dp, 0.dp)
            )
        }
    }
}

@Preview(name = "BVTextButton Light")
@Composable
fun BVTextButtonPreviewLight() {
    BVTextButtonPreview(darkTheme = false)
}

@Preview(name = "BVTextButton Dark")
@Composable
fun BVTextButtonPreviewDark() {
    BVTextButtonPreview(darkTheme = true)
}

@Composable
fun BVTextButtonPreview(darkTheme: Boolean) {
    BalanceVoteTheme(darkTheme) {
        BVTextButton(text = "TEST", onClick = { }, isSelected = false)
    }
}