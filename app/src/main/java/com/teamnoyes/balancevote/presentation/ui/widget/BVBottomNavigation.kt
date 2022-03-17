package com.teamnoyes.balancevote.presentation.ui.widget

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.teamnoyes.balancevote.R
import com.teamnoyes.balancevote.presentation.ui.screens.home.HomeScreen
import com.teamnoyes.balancevote.presentation.ui.screens.post.PostScreen
import com.teamnoyes.balancevote.presentation.ui.screens.settings.SettingsScreen

enum class Screen(val title: String, var icon: Int, var route: String) {
    HOME("Home", R.drawable.ic_home, "home"),
    POST("Post", R.drawable.ic_add, "post"),
    SETTINGS("Settings", R.drawable.ic_settings, "settings")
}

@Composable
fun BVBottomNavigation(currentRoute: String, navigateToRoute: (String) -> Unit) {
//    navController 위치는 이를 참조해야 하는 모든 컴포저블이 액세스 할 수 있어야 함
//    따라서 추후 이동할 필요 있음
//    val navController = rememberNavController()
    val items = listOf(Screen.HOME, Screen.POST, Screen.SETTINGS)

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 10.sp
                    )
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.5f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
//                    navController.navigate(item.route) {
//                        navController.graph.startDestinationRoute?.let { screen_route ->
//                            popUpTo(screen_route) {
//                                saveState = true
//                            }
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
                    navigateToRoute(item.route)
                }
            )
        }
    }
}

fun NavGraphBuilder.addHomeGraph() {
    composable(Screen.HOME.route) { HomeScreen() }
    composable(Screen.POST.route) { PostScreen() }
    composable(Screen.SETTINGS.route) { SettingsScreen() }
}

@Preview
@Composable
fun PreviewBVBottomNavigation() {
    BVBottomNavigation("test") {}
}