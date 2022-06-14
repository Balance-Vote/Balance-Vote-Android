package com.teamnoyes.balancevote.presentation.ui.screens.settings

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.teamnoyes.balancevote.presentation.ui.widget.BVTextButton

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp)) {
        BVTextButton(
            text = "OSS",
            onClick = {
                context.startActivity(Intent(context,
                    OssLicensesMenuActivity::class.java))
            },
            isSelected = false,
            horizontalAlignment = Arrangement.Center
        )
//        BVTextButton(
//            text = "Credit",
//            onClick = {},
//            isSelected = false,
//            horizontalAlignment = Arrangement.Start
//        )
//        BVTextButton(
//            text = "Notification",
//            onClick = {},
//            isSelected = false,
//            horizontalAlignment = Arrangement.Start
//        )
//        BVTextButton(
//            text = "Language",
//            onClick = {},
//            isSelected = false,
//            horizontalAlignment = Arrangement.Start
//        )
    }
}

@Preview
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen()
}