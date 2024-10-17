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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kevinvi.mangadisplay.navigation.BottomNavigationScreen
import com.kevinvi.mangadisplay.navigation.TemplateNavigator
import com.kevinvi.mangadisplay.navigation.isTopLevelScreenInHierarchy
import com.kevinvi.mangadisplay.navigation.rememberTemplateNavigator
import fr.npo.remotecontrolbluetooth.ui.theme.AppTheme
import kotlin.collections.forEach

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RemoteControlBluetoothApp(
    content: @Composable (PaddingValues, TemplateNavigator) -> Unit,
) {
    val navigator = rememberTemplateNavigator()

    val navBackStackEntry by TemplateNavigator.navController.currentBackStackEntryAsState()

    Scaffold(
        content = { scaffoldPadding ->
            content(scaffoldPadding, navigator)
        },
        bottomBar = {
            BottomNavigationBar(
                items = TemplateNavigator.Companion.destinations,
                currentDestination = NavBackStackEntry.destination,
            ) { screen ->
                TemplateNavigator.navigateToScreen(screen)
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
                        selected -> BottomNavigationScreen.selectedIcon
                        else -> BottomNavigationScreen.unselectedIcon
                    }
                    when (icon) {
                        is fr.npo.remotecontrolbluetooth.core.ui.Icon.ImageVectorIcon -> Icon(
                            imageVector = fr.npo.remotecontrolbluetooth.core.ui.Icon.ImageVectorIcon.imageVector,
                            contentDescription = stringResource(BottomNavigationScreen.nameResourceId),
                        )

                        is fr.npo.remotecontrolbluetooth.core.ui.Icon.DrawableResIcon -> Icon(
                            painter = painterResource(fr.npo.remotecontrolbluetooth.core.ui.Icon.DrawableResIcon.id),
                            contentDescription = stringResource(BottomNavigationScreen.nameResourceId),
                        )
                    }
                },
                label = { Text(stringResource(BottomNavigationScreen.nameResourceId)) },
                selected = selected,
                onClick = { onClick(screen) },
            )
        }
    }
}

@PreviewLightDark
@Composable
fun DefaultPreview() {
    fr.npo.remotecontrolbluetooth.ui.theme.AppTheme {
        RemoteControlBluetoothApp { _, _ -> }
    }
}
