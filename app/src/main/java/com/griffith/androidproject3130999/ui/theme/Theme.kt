// Radu Lupu 3130999

package com.griffith.androidproject3130999.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme (
    primary = AppBlue,
    secondary = AppGreen,
    background = AppBgLight,
    surface = AppCardBg
)

// Main application theme wrapper
@Composable
fun AndroidProject3130999Theme( content: @Composable () -> Unit){
    MaterialTheme (
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
