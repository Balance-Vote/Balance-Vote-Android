package com.teamnoyes.balancevote

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.google.accompanist.insets.ProvideWindowInsets
import com.teamnoyes.balancevote.presentation.ui.theme.BalanceVoteTheme
import com.teamnoyes.balancevote.presentation.ui.widget.BVAppBar
import com.teamnoyes.balancevote.presentation.ui.widget.BVBottomNavigation

@Composable
fun BVApp() {
    ProvideWindowInsets {
        BalanceVoteTheme() {
            Scaffold(
                topBar = { BVAppBar(title = "") },
                bottomBar = { BVBottomNavigation() }
            ) {
//                NavHost(navController = , startDestination = , builder = ) {
//
//                }
            }
        }
    }
}