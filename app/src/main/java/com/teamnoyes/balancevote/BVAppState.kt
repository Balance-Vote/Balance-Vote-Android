package com.teamnoyes.balancevote

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

object BVDestinations {
    const val HOME = "home"
    const val POST = "post"
    const val SETTINGS = "settings"
}

@Composable
fun rememberBVAppState(
    navController: NavHostController = rememberNavController(),
) = remember(navController) { BVAppState(navController) }

@Stable
class BVAppState(val navController: NavHostController) {

}