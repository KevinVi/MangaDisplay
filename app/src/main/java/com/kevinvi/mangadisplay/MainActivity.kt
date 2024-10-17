package com.kevinvi.mangadisplay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.DisposableEffect
import com.kevinvi.mangadisplay.navigation.MangaDisplayNavHost
import com.kevinvi.mangadisplay.navigation.MangaDisplayNavigator
import com.kevinvi.mangadisplay.ui.MangaDisplayApp
import com.kevinvi.mangadisplay.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // Update the dark content of the system bars to match the theme
            DisposableEffect(isSystemInDarkTheme()) {
                enableEdgeToEdge()
                onDispose {}
            }
            AppTheme(true, true) {
                MangaDisplayApp { innerPadding, navigator ->
                    MangaDisplayNavHost(
                        navController = navigator.navController,
                        startScreen = MangaDisplayNavigator.startScreen,
                        innerPadding = innerPadding,
                    )
                }
            }

        }
    }
}