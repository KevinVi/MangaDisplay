package com.kevinvi.common.motion

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.PathEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Path

@Stable
object MaterialMotion {

    @Stable
    val Standard = CubicBezierEasing(0.2f, 0.0f, 0f, 1.0f)

    @Stable
    val Emphasized = PathEasing(
        Path().apply {
            moveTo(0f, 0f)
            cubicTo(0.05f, 0f, 0.133333f, 0.06f, 0.166666f, 0.4f)
            cubicTo(0.208333f, 0.82f, 0.25f, 1f, 1f, 1f)
        },
    )

    @Stable
    fun <T> tweenScreen(duration: Int = 500): TweenSpec<T> = TweenSpec(
        durationMillis = duration,
        easing = Emphasized,
    )
}
