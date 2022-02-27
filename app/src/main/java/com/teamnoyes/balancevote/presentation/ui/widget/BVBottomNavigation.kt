package com.teamnoyes.balancevote.presentation.ui.widget

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.teamnoyes.balancevote.R

sealed class Screen(val title: String, var icon: Int, var route: String) {
    object Profile : Screen("Home", R.drawable.ic_home, "home")
    object Post : Screen("Post", R.drawable.ic_add, "post")
    object Settings : Screen("Settings", R.drawable.ic_settings, "settings")
}

@Composable
fun BVBottomNavigation() {
//    navController 위치는 이를 참조해야 하는 모든 컴포저블이 액세스 할 수 있어야 함
//    따라서 추후 이동할 필요 있음
    val navController = rememberNavController()
    val items = listOf(Screen.Profile, Screen.Post, Screen.Settings)

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
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
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewBVBottomNavigation() {
        BVBottomNavigation()
}