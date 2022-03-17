package com.teamnoyes.balancevote

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.accompanist.insets.ProvideWindowInsets
import com.teamnoyes.balancevote.presentation.ui.theme.BalanceVoteTheme
import com.teamnoyes.balancevote.presentation.ui.widget.BVAppBar
import com.teamnoyes.balancevote.presentation.ui.widget.BVBottomNavigation
import com.teamnoyes.balancevote.presentation.ui.widget.Screen
import com.teamnoyes.balancevote.presentation.ui.widget.addHomeGraph

@Composable
fun BVApp() {
    ProvideWindowInsets {
        BalanceVoteTheme {
            val appState = rememberBVAppState()
            val test = appState.test
            var turnTest = false
            Scaffold(
                topBar = { BVAppBar(title = appState.currentRoute?.uppercase() ?: "") },
                bottomBar = {
                    if(turnTest) {
                        BVBottomNavigation(
                            currentRoute = appState.currentRoute ?: "",
                            navigateToRoute = appState::navigateBottomNav
                        )
                    }
                }
            ) { pv ->
//                화면의 중간, 내용 부분
                NavHost(
                    navController = appState.navController,
                    startDestination = BVDestinations.HOME,
                    modifier = Modifier.padding(pv)
                ) {
//                    bvNavGraph(upPress = {})
                    addHomeGraph()
                    turnTest = true
                }
            }
        }
    }
}

// Home -> Vote 간의 Nested Graph. upPress: 상단 바의 뒤로 가기 버튼
private fun NavGraphBuilder.bvNavGraph(upPress: () -> Unit) {
    navigation(startDestination = Screen.HOME.route, route = BVDestinations.HOME) {
        addHomeGraph()
    }
}