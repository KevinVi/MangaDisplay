package com.kevinvi.mangadisplay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kevinvi.mangadisplay.navigation.MangaDisplayNavHost
import com.kevinvi.mangadisplay.navigation.MangaDisplayNavigator
import com.kevinvi.mangadisplay.ui.MangaDisplayApp
import com.kevinvi.popular.ui.PopulareScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{

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