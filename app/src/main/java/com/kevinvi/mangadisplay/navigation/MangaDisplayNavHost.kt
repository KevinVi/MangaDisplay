package com.kevinvi.mangadisplay.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kevinvi.common.motion.MaterialMotion.tweenScreen
import com.kevinvi.popular.navigation.addPopularRoute
import com.kevinvi.search.navigation.addSearchRoute

@Composable
fun MangaDisplayNavHost(
    navController: NavHostController,
    startScreen: BottomNavigationScreen,
    innerPadding: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = startScreen.route,
        modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
        enterTransition = { fadeIn(animationSpec = tweenScreen()) },
        exitTransition = { fadeOut(animationSpec = tweenScreen()) },
        popEnterTransition = { fadeIn(animationSpec = tweenScreen()) },
        popExitTransition = { fadeOut(animationSpec = tweenScreen()) },
    ) {
        addPopularRoute(navController)
        addSearchRoute(navController)
    }
}
