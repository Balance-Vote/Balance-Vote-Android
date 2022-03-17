package com.teamnoyes.balancevote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teamnoyes.balancevote.presentation.ui.screens.home.HomeScreen
import com.teamnoyes.balancevote.presentation.ui.screens.post.PostScreen
import com.teamnoyes.balancevote.presentation.ui.screens.settings.SettingsScreen
import com.teamnoyes.balancevote.presentation.ui.theme.BalanceVoteTheme
import com.teamnoyes.balancevote.presentation.ui.widget.BVInput
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BalanceVoteTheme {
                // A surface container using the 'background' color from the theme
                BVApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BalanceVoteTheme {
        BVApp()
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    BalanceVoteTheme {
        SettingsScreen()
    }
}