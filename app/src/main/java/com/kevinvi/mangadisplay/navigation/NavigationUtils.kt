package com.kevinvi.mangadisplay.navigation

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy

fun NavDestination?.isTopLevelScreenInHierarchy(screen: BottomNavigationScreen) =
    this?.hierarchy?.any {
        it.route?.contains(screen.route, true) ?: false
    } ?: false
