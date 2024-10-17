package com.kevinvi.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kevinvi.search.ui.SearchScreen

const val SEARCH_ROUTE = "search"

fun NavController.navigateToSearch(navOptions: NavOptions) {
    navigate(SEARCH_ROUTE, navOptions)
}

fun NavGraphBuilder.addSearchRoute(navController: NavHostController) {
    composable(SEARCH_ROUTE) { SearchScreen(navController) }
}
