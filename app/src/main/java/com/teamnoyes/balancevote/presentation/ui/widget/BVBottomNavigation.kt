package com.teamnoyes.balancevote.presentation.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teamnoyes.balancevote.R
import com.teamnoyes.balancevote.presentation.ui.theme.BackGround
import com.teamnoyes.balancevote.presentation.ui.theme.MainColor

enum class BottomNavScreen(val title: String, var icon: Int, var route: String) {
    HOME("Home", R.drawable.ic_home, "main/home"),
    POST("Post", R.drawable.ic_add, "main/post"),
    SETTINGS("Settings", R.drawable.ic_settings, "main/settings")
}

@Composable
fun BVBottomNavigation(currentRoute: String, navigateToRoute: (String) -> Unit) {
//    navController 위치는 이를 참조해야 하는 모든 컴포저블이 액세스 할 수 있어야 함
//    따라서 추후 이동할 필요 있음
//    val navController = rememberNavController()
    val items = listOf(BottomNavScreen.HOME, BottomNavScreen.POST, BottomNavScreen.SETTINGS)
    Surface(modifier = Modifier
        .background(BackGround)
        .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackGround)
                .height(64.dp)
                .background(Color.White, RoundedCornerShape(32.dp))
                .border(2.dp, Color.DarkGray, RoundedCornerShape(32.dp)),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
//                Main 색상 정해서 사용하자.
//                val color = if (item.route == currentRoute) Color.Black else Color.LightGray
                val color = Color.DarkGray

                IconButton(modifier = if (item.route == currentRoute) Modifier.background(MainColor, RoundedCornerShape(100)) else Modifier,
                    onClick = { navigateToRoute(item.route) }) {
                    Icon(painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        tint = color)
                }

//                BottomNavigationItem(
//                    icon = {
//                        Icon(painterResource(id = item.icon),
//                            contentDescription = item.title)
//                    },
//                    selectedContentColor = Color.Black,
//                    unselectedContentColor = Color.Black.copy(0.5f),
//                    selected = currentRoute == item.route,
//                    onClick = { navigateToRoute(item.route) }
//                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewBVBottomNavigation() {
    BVBottomNavigation("test") {}
}