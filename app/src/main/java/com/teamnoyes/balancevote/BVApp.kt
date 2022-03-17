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
import com.teamnoyes.balancevote.presentation.ui.screens.home.HomeScreen
import com.teamnoyes.balancevote.presentation.ui.screens.post.PostScreen
import com.teamnoyes.balancevote.presentation.ui.screens.settings.SettingsScreen
import com.teamnoyes.balancevote.presentation.ui.screens.vote.VoteScreen
import com.teamnoyes.balancevote.presentation.ui.theme.BalanceVoteTheme
import com.teamnoyes.balancevote.presentation.ui.widget.BVAppBar
import com.teamnoyes.balancevote.presentation.ui.widget.BVBottomNavigation
import com.teamnoyes.balancevote.presentation.ui.widget.Screen

@Composable
fun BVApp() {
    ProvideWindowInsets {
        BalanceVoteTheme {
            val appState = rememberBVAppState()
            val test = appState.test
            var turnTest = false
            Scaffold(
                topBar = {
                    BVAppBar(
                        title = appState.currentRoute?.uppercase() ?: "",
                        isNavigationOn = !appState.isNavigationOff
                    )
                },
                bottomBar = {
                    if (turnTest) {
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

fun NavGraphBuilder.addHomeGraph() {
    composable(Screen.HOME.route) { HomeScreen() }
    composable(Screen.POST.route) { PostScreen() }
    composable(Screen.SETTINGS.route) { SettingsScreen() }
}

fun NavGraphBuilder.addVoteGraph() {
    composable(Vote.VOTE.route) { VoteScreen() }
}


enum class Vote(val title: String, val route: String) {
    VOTE("Vote", "home/vote"),
    DETAIL("Detail", "home/detail")
}

// Home -> Vote 간의 Nested Graph. upPress: 상단 바의 뒤로 가기 버튼
private fun NavGraphBuilder.bvNavGraph(upPress: () -> Unit) {
    navigation(startDestination = Screen.HOME.route, route = BVDestinations.HOME) {
//        addHomeGraph()
    }
}