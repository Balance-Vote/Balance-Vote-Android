package com.teamnoyes.balancevote

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.teamnoyes.balancevote.presentation.ui.widget.Screen

object BVDestinations {
    const val HOME = "home"
}

@Composable
fun rememberBVAppState(
    navController: NavHostController = rememberNavController(),
) = remember(navController) { BVAppState(navController) }

@Stable
class BVAppState(val navController: NavHostController) {

    val topUpToOffs = Screen.values()
    private val topUpToOffRoutes = topUpToOffs.map { it.route }

    val isNavigationOff: Boolean
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route in topUpToOffRoutes

    val init: String?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route

    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun navigateBottomNav(route: String) {
        if (route != currentRoute) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                popUpTo(findStartDestination(navController.graph).id) {
                    saveState = true
                }
            }
        }
    }

    private val NavGraph.startDestination: NavDestination?
        get() = findNode(startDestinationId)

    private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
        return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
    }
}