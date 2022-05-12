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
import com.teamnoyes.balancevote.presentation.ui.screens.entry.EntryScreen
import com.teamnoyes.balancevote.presentation.ui.screens.home.HomeScreen
import com.teamnoyes.balancevote.presentation.ui.screens.home.HomeViewModel
import com.teamnoyes.balancevote.presentation.ui.screens.post.PostScreen
import com.teamnoyes.balancevote.presentation.ui.screens.post.PostViewModel
import com.teamnoyes.balancevote.presentation.ui.screens.settings.SettingsScreen
import com.teamnoyes.balancevote.presentation.ui.screens.splash.SplashScreen
import com.teamnoyes.balancevote.presentation.ui.screens.vote.VoteScreen
import com.teamnoyes.balancevote.presentation.ui.screens.vote.detail.DetailVoteScreen
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
                            title = appState.currentRoute?.uppercase() ?: "",
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
                        EntryScreen(navController = appState.navController)
                    }
                    addMainGraph(appState.navController) { msg ->
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

fun NavGraphBuilder.addMainGraph(navController: NavController, snackbarEvent: (String) -> Unit) {
    navigation(startDestination = "main/home", route = "main") {
        composable(BottomNavScreen.HOME.route) {
            val homeViewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(homeViewModel, navController)
        }
        composable(VotePostScreen.VOTE.route) { VoteScreen(navController = navController) }
        composable(VotePostScreen.DETAIL.route) { DetailVoteScreen(navController) }
        composable(BottomNavScreen.POST.route) {
            val postViewModel = hiltViewModel<PostViewModel>()
            PostScreen(postViewModel, navController, snackbarEvent)
        }
        composable(BottomNavScreen.SETTINGS.route) { SettingsScreen() }
    }
}