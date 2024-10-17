package com.kevinvi.mangadisplay.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.kevinvi.mangadisplay.navigation.BottomNavigationScreen.Feature2
import com.kevinvi.mangadisplay.navigation.BottomNavigationScreen.Popular
import com.kevinvi.popular.navigation.navigateToFeature1
import com.kevinvi.popular.navigation.navigateToPopular
import fr.npo.remotecontrolbluetooth.feature.feature2.ui.navigation.navigateToFeature2

@Composable
fun rememberTemplateNavigator(
    navController: NavHostController = rememberNavController(),
) = remember(navController) {
    TemplateNavigator(navController)
}

class TemplateNavigator(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentScreen: BottomNavigationScreen
        @Composable get() = when (currentDestination?.route) {
            Popular.route -> Popular
            Feature2.route -> Feature2
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
            Feature2 -> navController.navigateToFeature2(navOptions)
        }
    }

    companion object {
        val destinations = listOf(Popular, Search)

        val startScreen = destinations.first()
    }
}
