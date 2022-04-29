package com.teamnoyes.balancevote

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.teamnoyes.balancevote.presentation.ui.widget.BottomNavScreen

@Composable
fun rememberBVAppState(
    navController: NavHostController = rememberNavController(),
) = remember(navController) { BVAppState(navController) }

@Stable
class BVAppState(val navController: NavHostController) {

    val topUpToOffs = BottomNavScreen.values()
    private val topUpToOffRoutes = topUpToOffs.map { it.route }

    val isNavigationOff: Boolean
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route in topUpToOffRoutes

    val barTabs = BottomNavScreen.values()
    private val barTabsRoutes = barTabs.map { it.route }

    val showBars: Boolean
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route in barTabsRoutes

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