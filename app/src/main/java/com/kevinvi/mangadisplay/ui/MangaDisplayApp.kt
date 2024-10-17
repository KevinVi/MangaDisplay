package com.kevinvi.mangadisplay.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kevinvi.mangadisplay.navigation.BottomNavigationScreen
import com.kevinvi.mangadisplay.navigation.MangaDisplayNavigator
import com.kevinvi.mangadisplay.navigation.isTopLevelScreenInHierarchy
import com.kevinvi.mangadisplay.navigation.rememberNavigator
import com.kevinvi.ui.Icon.DrawableResIcon
import com.kevinvi.ui.Icon.ImageVectorIcon
import kotlin.collections.forEach

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MangaDisplayApp(
    content: @Composable (PaddingValues, MangaDisplayNavigator) -> Unit,
) {
    val navigator = rememberNavigator()

    val navBackStackEntry by navigator.navController.currentBackStackEntryAsState()

    Scaffold(
        content = { scaffoldPadding ->
            content(scaffoldPadding, navigator)
        },
        bottomBar = {
            BottomNavigationBar(
                items = MangaDisplayNavigator.destinations,
                currentDestination = navBackStackEntry?.destination,
            ) { screen ->
                navigator.navigateToScreen(screen)
            }
        },
    )
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationScreen>,
    currentDestination: NavDestination?,
    onClick: (screen: BottomNavigationScreen) -> Unit,
) {
    NavigationBar {
        items.forEach { screen ->
            val selected = currentDestination.isTopLevelScreenInHierarchy(screen)
            NavigationBarItem(
                icon = {
                    val icon = when {
                        selected -> screen.selectedIcon
                        else -> screen.unselectedIcon
                    }
                    when (icon) {
                        is ImageVectorIcon -> Icon(
                            imageVector = icon.imageVector,
                            contentDescription = (screen.nameResourceId),
                        )

                        is DrawableResIcon -> Icon(
                            painter = painterResource(icon.id),
                            contentDescription = (screen.nameResourceId),
                        )
                    }
                },
                label = { Text((screen.nameResourceId)) },
                selected = selected,
                onClick = { onClick(screen) },
            )
        }
    }
}

