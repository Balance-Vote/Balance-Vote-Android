package com.teamnoyes.balancevote

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.accompanist.insets.ProvideWindowInsets
import com.teamnoyes.balancevote.presentation.ui.screens.home.HomeScreen
import com.teamnoyes.balancevote.presentation.ui.screens.home.HomeViewModel
import com.teamnoyes.balancevote.presentation.ui.screens.post.PostScreen
import com.teamnoyes.balancevote.presentation.ui.screens.post.PostViewModel
import com.teamnoyes.balancevote.presentation.ui.screens.settings.SettingsScreen
import com.teamnoyes.balancevote.presentation.ui.screens.vote.VoteScreen
import com.teamnoyes.balancevote.presentation.ui.theme.BalanceVoteTheme
import com.teamnoyes.balancevote.presentation.ui.widget.BVAppBar
import com.teamnoyes.balancevote.presentation.ui.widget.BVBottomNavigation
import com.teamnoyes.balancevote.presentation.ui.widget.BottomNavScreen
import kotlinx.coroutines.launch

@Composable
fun BVApp() {
    ProvideWindowInsets {
        BalanceVoteTheme {
            val appState = rememberBVAppState()
            val showBars = appState.showBars
            var turnTest = false
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    if(showBars) {
                        BVAppBar(
                            title = appState.currentRoute?.uppercase() ?: "",
                            isNavigationOn = !appState.isNavigationOff
                        )
                    }
                },
                bottomBar = {
                    if (showBars) {
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
                    addHomeGraph(appState.navController) { msg ->
                        scope.launch {
                            scaffoldState.snackbarHostState
                                .showSnackbar(msg)
                        }
                    }
                    turnTest = true
                }
            }
        }
    }
}

fun NavGraphBuilder.addHomeGraph(navController: NavController, snackbarEvent: (String) -> Unit) {
    composable(BottomNavScreen.HOME.route) {
        val homeViewModel = hiltViewModel<HomeViewModel>()
        HomeScreen(homeViewModel, navController)
    }
    composable(BottomNavScreen.POST.route) {
        val postViewModel = hiltViewModel<PostViewModel>()
        PostScreen(postViewModel, navController, snackbarEvent)
    }
    composable(BottomNavScreen.SETTINGS.route) { SettingsScreen() }
}

//fun NavGraphBuilder.addVoteGraph() {
//    composable(Vote.VOTE.route) { VoteScreen() }
//}

enum class VotePostScreen(val title: String, val route: String) {
    VOTE("Vote", "home/vote"),
    DETAIL("Detail", "home/detail")
}

// Home -> Vote 간의 Nested Graph. upPress: 상단 바의 뒤로 가기 버튼
private fun NavGraphBuilder.bvNavGraph(upPress: () -> Unit) {
    navigation(startDestination = BottomNavScreen.HOME.route, route = BVDestinations.HOME) {
//        addHomeGraph()
    }
}