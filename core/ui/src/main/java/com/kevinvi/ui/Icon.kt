package com.kevinvi.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.kevinvi.ui.Icon.DrawableResIcon
import com.kevinvi.ui.Icon.ImageVectorIcon

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResIcon(@DrawableRes val id: Int) : Icon()
}

@Composable
fun ImageIcon(
    icon: Icon,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Companion.Center,
    contentScale: ContentScale = ContentScale.Companion.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
) {
    when (icon) {
        is ImageVectorIcon -> {
            Image(
                imageVector = icon.imageVector,
                contentDescription = contentDescription,
                modifier = modifier,
                alignment = alignment,
                contentScale = contentScale,
                alpha = alpha,
                colorFilter = colorFilter,
            )
        }

        is DrawableResIcon -> {
            Image(
                painter = painterResource(icon.id),
                contentDescription = contentDescription,
                modifier = modifier,
                alignment = alignment,
                contentScale = contentScale,
                alpha = alpha,
                colorFilter = colorFilter,
            )
        }
    }
}