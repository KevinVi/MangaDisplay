package com.kevinvi.popular.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kevinvi.popular.ui.PopulareScreen

const val POPULAR_ROUTE = "popular"

fun NavController.navigateToPopular(navOptions: NavOptions) {
    navigate(POPULAR_ROUTE, navOptions)
}

fun NavGraphBuilder.addPopularRoute(navController: NavHostController) {
    composable(POPULAR_ROUTE) { PopulareScreen(navController) }
}
