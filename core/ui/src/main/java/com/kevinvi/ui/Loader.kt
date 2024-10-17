package com.kevinvi.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Loader(
    isLoading: Boolean,
) {
    if (isLoading) {

        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    }
}