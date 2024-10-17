package com.kevinvi.mangadisplay.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.kevinvi.mangadisplay.navigation.BottomNavigationScreen.Popular
import com.kevinvi.mangadisplay.navigation.BottomNavigationScreen.Search
import com.kevinvi.popular.navigation.navigateToPopular
import com.kevinvi.search.navigation.navigateToSearch

@Composable
fun rememberNavigator(
    navController: NavHostController = rememberNavController(),
) = remember(navController) {
    MangaDisplayNavigator(navController)
}

class MangaDisplayNavigator(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentScreen: BottomNavigationScreen
        @Composable get() = when (currentDestination?.route) {
            Popular.route -> Popular
            Search.route -> Search
            else -> startScreen
        }

    fun navigateToScreen(screen: BottomNavigationScreen) {
        val navOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = false
        }

        when (screen) {
            Popular -> navController.navigateToPopular(navOptions)
            Search -> navController.navigateToSearch(navOptions)
        }
    }

    companion object {
        val destinations = listOf(Popular, Search)

        val startScreen = destinations.first()
    }
}
