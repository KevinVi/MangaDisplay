package com.kevinvi.mangadisplay.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons.Rounded
import androidx.compose.material.icons.rounded.BugReport
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import com.kevinvi.popular.navigation.POPULAR_ROUTE
import com.kevinvi.search.navigation.SEARCH_ROUTE
import com.kevinvi.ui.Icon
import com.kevinvi.ui.Icon.ImageVectorIcon

sealed class BottomNavigationScreen(
    val route: String,
    val nameResourceId: String,
    val selectedIcon: Icon,
    val unselectedIcon: Icon = selectedIcon,
) {
    data object Popular : BottomNavigationScreen(
        route = POPULAR_ROUTE,
        nameResourceId = "Popular",
        selectedIcon = ImageVectorIcon(Rounded.Home),
    )

    data object Search : BottomNavigationScreen(
        route = SEARCH_ROUTE,
        nameResourceId = "Search",
        selectedIcon = ImageVectorIcon(Rounded.Search),
    )

}
