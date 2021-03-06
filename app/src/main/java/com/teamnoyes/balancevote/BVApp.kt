package com.teamnoyes.balancevote

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.insets.ProvideWindowInsets
import com.teamnoyes.balancevote.presentation.ui.screens.entry.EntryScreen
import com.teamnoyes.balancevote.presentation.ui.screens.home.HomeScreen
import com.teamnoyes.balancevote.presentation.ui.screens.home.HomeViewModel
import com.teamnoyes.balancevote.presentation.ui.screens.post.PostScreen
import com.teamnoyes.balancevote.presentation.ui.screens.post.PostViewModel
import com.teamnoyes.balancevote.presentation.ui.screens.settings.SettingsScreen
import com.teamnoyes.balancevote.presentation.ui.screens.splash.SplashScreen
import com.teamnoyes.balancevote.presentation.ui.screens.vote.VoteScreen
import com.teamnoyes.balancevote.presentation.ui.screens.vote.VoteViewModel
import com.teamnoyes.balancevote.presentation.ui.screens.vote.detail.DetailVoteScreen
import com.teamnoyes.balancevote.presentation.ui.screens.vote.detail.DetailVoteViewModel
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
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    if (appState.showBars) {
                        BVAppBar(
                            title = appState.currentTitle ?: "",
                            isNavigationOn = appState.showUpToButton,
                            onNavIconPressed = { appState.upPress() }
                        )
                    }
                },
                bottomBar = {
                    if (appState.showBars) {
                        BVBottomNavigation(
                            currentRoute = appState.currentRoute ?: "",
                            navigateToRoute = appState::navigateBottomNav
                        )
                    }
                }
            ) { pv ->
                NavHost(
                    navController = appState.navController,
                    startDestination = "entry",
                    modifier = Modifier.padding(pv)
                ) {
                    composable("splash") {
                        SplashScreen(navController = appState.navController)
                    }
                    composable("entry") {
                        EntryScreen(navController = appState.navController) {
                            appState.nickname.value = it
                        }
                    }
                    addMainGraph(appState.navController, appState.nickname) { msg ->
                        scope.launch {
                            scaffoldState.snackbarHostState
                                .showSnackbar(msg)
                        }
                    }
                }
            }
        }
    }
}

fun NavGraphBuilder.addMainGraph(
    navController: NavController,
    nickname: MutableState<String>,
    snackbarEvent: (String) -> Unit,
) {
    navigation(startDestination = "main/home", route = "main") {
        composable(BottomNavScreen.HOME.route) {
            val homeViewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(homeViewModel, navController)
        }
        composable(VotePostScreen.VOTE.route,
            arguments = listOf(navArgument("postId") { type = NavType.StringType },
                navArgument("left") { type = NavType.StringType },
                navArgument("right") { type = NavType.StringType })) {
            val voteViewModel = hiltViewModel<VoteViewModel>()
            VoteScreen(viewModel = voteViewModel,
                postId = it.arguments?.getString("postId") ?: "",
                leftTopic = it.arguments?.getString("left") ?: "",
                rightTopic = it.arguments?.getString("right") ?: "",
                navController = navController,
                snackbarEvent = snackbarEvent,
                nickname = nickname)
        }
        composable(VotePostScreen.DETAIL.route) {
            val detailVoteViewModel = hiltViewModel<DetailVoteViewModel>()
            DetailVoteScreen(viewModel = detailVoteViewModel,
                navController = navController,
                postId = it.arguments?.getString("postId") ?: "",
                nickname = nickname)
        }
        composable(BottomNavScreen.POST.route) {
            val postViewModel = hiltViewModel<PostViewModel>()
            PostScreen(postViewModel, navController, nickname, snackbarEvent)
        }
        composable(BottomNavScreen.SETTINGS.route) { SettingsScreen() }
    }
}