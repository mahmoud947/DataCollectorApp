package com.mahmoud.systemdesign.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color



@Composable
fun BoxScope.VerticalGradientBG(
    alpha: Float = 0.5f,
    colors: List<Color> = listOf(
        MaterialTheme.colorScheme.primary.copy(alpha = alpha),
        MaterialTheme.colorScheme.secondary.copy(alpha = alpha)
    )
) {
    this@VerticalGradientBG.apply {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = colors
                    )
                )
                .fillMaxSize()
        )
    }
}

@Composable
fun Modifier.verticalGradientBG(
    alpha: Float = 0.5f,
    colors: List<Color> = listOf(
        MaterialTheme.colorScheme.primary.copy(alpha = alpha),
        MaterialTheme.colorScheme.secondary.copy(alpha = alpha)
    )
): Modifier {
    return this.then(
        Modifier.background(
            brush = Brush.verticalGradient(colors = colors)
        )
    )
}

@Composable
fun Modifier.horizontalGradientBG(
    alpha: Float = 0.5f,
    enabled: Boolean = true,
    colors: List<Color> = listOf(
        MaterialTheme.colorScheme.primary.copy(alpha = alpha),
        MaterialTheme.colorScheme.secondary.copy(alpha = alpha)
    ),
    disabledColors: List<Color> = listOf(
        Color.Gray.copy(alpha = 0.6f),
        Color.Gray.copy(alpha = 0.6f)
    )
): Modifier {
    return this.then(
        Modifier.background(
            brush = Brush.horizontalGradient(colors = if (enabled) colors else disabledColors)
        )
    )
}

