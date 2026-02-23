package com.example.compose
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.chatbot.ui.theme.AppTypography
import com.example.chatbot.ui.theme.backgroundDark
import com.example.chatbot.ui.theme.backgroundDarkHighContrast
import com.example.chatbot.ui.theme.backgroundDarkMediumContrast
import com.example.chatbot.ui.theme.backgroundLight
import com.example.chatbot.ui.theme.backgroundLightHighContrast
import com.example.chatbot.ui.theme.backgroundLightMediumContrast
import com.example.chatbot.ui.theme.errorContainerDark
import com.example.chatbot.ui.theme.errorContainerDarkHighContrast
import com.example.chatbot.ui.theme.errorContainerDarkMediumContrast
import com.example.chatbot.ui.theme.errorContainerLight
import com.example.chatbot.ui.theme.errorContainerLightHighContrast
import com.example.chatbot.ui.theme.errorContainerLightMediumContrast
import com.example.chatbot.ui.theme.errorDark
import com.example.chatbot.ui.theme.errorDarkHighContrast
import com.example.chatbot.ui.theme.errorDarkMediumContrast
import com.example.chatbot.ui.theme.errorLight
import com.example.chatbot.ui.theme.errorLightHighContrast
import com.example.chatbot.ui.theme.errorLightMediumContrast
import com.example.chatbot.ui.theme.inverseOnSurfaceDark
import com.example.chatbot.ui.theme.inverseOnSurfaceDarkHighContrast
import com.example.chatbot.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.example.chatbot.ui.theme.inverseOnSurfaceLight
import com.example.chatbot.ui.theme.inverseOnSurfaceLightHighContrast
import com.example.chatbot.ui.theme.inverseOnSurfaceLightMediumContrast
import com.example.chatbot.ui.theme.inversePrimaryDark
import com.example.chatbot.ui.theme.inversePrimaryDarkHighContrast
import com.example.chatbot.ui.theme.inversePrimaryDarkMediumContrast
import com.example.chatbot.ui.theme.inversePrimaryLight
import com.example.chatbot.ui.theme.inversePrimaryLightHighContrast
import com.example.chatbot.ui.theme.inversePrimaryLightMediumContrast
import com.example.chatbot.ui.theme.inverseSurfaceDark
import com.example.chatbot.ui.theme.inverseSurfaceDarkHighContrast
import com.example.chatbot.ui.theme.inverseSurfaceDarkMediumContrast
import com.example.chatbot.ui.theme.inverseSurfaceLight
import com.example.chatbot.ui.theme.inverseSurfaceLightHighContrast
import com.example.chatbot.ui.theme.inverseSurfaceLightMediumContrast
import com.example.chatbot.ui.theme.onBackgroundDark
import com.example.chatbot.ui.theme.onBackgroundDarkHighContrast
import com.example.chatbot.ui.theme.onBackgroundDarkMediumContrast
import com.example.chatbot.ui.theme.onBackgroundLight
import com.example.chatbot.ui.theme.onBackgroundLightHighContrast
import com.example.chatbot.ui.theme.onBackgroundLightMediumContrast
import com.example.chatbot.ui.theme.onErrorContainerDark
import com.example.chatbot.ui.theme.onErrorContainerDarkHighContrast
import com.example.chatbot.ui.theme.onErrorContainerDarkMediumContrast
import com.example.chatbot.ui.theme.onErrorContainerLight
import com.example.chatbot.ui.theme.onErrorContainerLightHighContrast
import com.example.chatbot.ui.theme.onErrorContainerLightMediumContrast
import com.example.chatbot.ui.theme.onErrorDark
import com.example.chatbot.ui.theme.onErrorDarkHighContrast
import com.example.chatbot.ui.theme.onErrorDarkMediumContrast
import com.example.chatbot.ui.theme.onErrorLight
import com.example.chatbot.ui.theme.onErrorLightHighContrast
import com.example.chatbot.ui.theme.onErrorLightMediumContrast
import com.example.chatbot.ui.theme.onPrimaryContainerDark
import com.example.chatbot.ui.theme.onPrimaryContainerDarkHighContrast
import com.example.chatbot.ui.theme.onPrimaryContainerDarkMediumContrast
import com.example.chatbot.ui.theme.onPrimaryContainerLight
import com.example.chatbot.ui.theme.onPrimaryContainerLightHighContrast
import com.example.chatbot.ui.theme.onPrimaryContainerLightMediumContrast
import com.example.chatbot.ui.theme.onPrimaryDark
import com.example.chatbot.ui.theme.onPrimaryDarkHighContrast
import com.example.chatbot.ui.theme.onPrimaryDarkMediumContrast
import com.example.chatbot.ui.theme.onPrimaryLight
import com.example.chatbot.ui.theme.onPrimaryLightHighContrast
import com.example.chatbot.ui.theme.onPrimaryLightMediumContrast
import com.example.chatbot.ui.theme.onSecondaryContainerDark
import com.example.chatbot.ui.theme.onSecondaryContainerDarkHighContrast
import com.example.chatbot.ui.theme.onSecondaryContainerDarkMediumContrast
import com.example.chatbot.ui.theme.onSecondaryContainerLight
import com.example.chatbot.ui.theme.onSecondaryContainerLightHighContrast
import com.example.chatbot.ui.theme.onSecondaryContainerLightMediumContrast
import com.example.chatbot.ui.theme.onSecondaryDark
import com.example.chatbot.ui.theme.onSecondaryDarkHighContrast
import com.example.chatbot.ui.theme.onSecondaryDarkMediumContrast
import com.example.chatbot.ui.theme.onSecondaryLight
import com.example.chatbot.ui.theme.onSecondaryLightHighContrast
import com.example.chatbot.ui.theme.onSecondaryLightMediumContrast
import com.example.chatbot.ui.theme.onSurfaceDark
import com.example.chatbot.ui.theme.onSurfaceDarkHighContrast
import com.example.chatbot.ui.theme.onSurfaceDarkMediumContrast
import com.example.chatbot.ui.theme.onSurfaceLight
import com.example.chatbot.ui.theme.onSurfaceLightHighContrast
import com.example.chatbot.ui.theme.onSurfaceLightMediumContrast
import com.example.chatbot.ui.theme.onSurfaceVariantDark
import com.example.chatbot.ui.theme.onSurfaceVariantDarkHighContrast
import com.example.chatbot.ui.theme.onSurfaceVariantDarkMediumContrast
import com.example.chatbot.ui.theme.onSurfaceVariantLight
import com.example.chatbot.ui.theme.onSurfaceVariantLightHighContrast
import com.example.chatbot.ui.theme.onSurfaceVariantLightMediumContrast
import com.example.chatbot.ui.theme.onTertiaryContainerDark
import com.example.chatbot.ui.theme.onTertiaryContainerDarkHighContrast
import com.example.chatbot.ui.theme.onTertiaryContainerDarkMediumContrast
import com.example.chatbot.ui.theme.onTertiaryContainerLight
import com.example.chatbot.ui.theme.onTertiaryContainerLightHighContrast
import com.example.chatbot.ui.theme.onTertiaryContainerLightMediumContrast
import com.example.chatbot.ui.theme.onTertiaryDark
import com.example.chatbot.ui.theme.onTertiaryDarkHighContrast
import com.example.chatbot.ui.theme.onTertiaryDarkMediumContrast
import com.example.chatbot.ui.theme.onTertiaryLight
import com.example.chatbot.ui.theme.onTertiaryLightHighContrast
import com.example.chatbot.ui.theme.onTertiaryLightMediumContrast
import com.example.chatbot.ui.theme.outlineDark
import com.example.chatbot.ui.theme.outlineDarkHighContrast
import com.example.chatbot.ui.theme.outlineDarkMediumContrast
import com.example.chatbot.ui.theme.outlineLight
import com.example.chatbot.ui.theme.outlineLightHighContrast
import com.example.chatbot.ui.theme.outlineLightMediumContrast
import com.example.chatbot.ui.theme.outlineVariantDark
import com.example.chatbot.ui.theme.outlineVariantDarkHighContrast
import com.example.chatbot.ui.theme.outlineVariantDarkMediumContrast
import com.example.chatbot.ui.theme.outlineVariantLight
import com.example.chatbot.ui.theme.outlineVariantLightHighContrast
import com.example.chatbot.ui.theme.outlineVariantLightMediumContrast
import com.example.chatbot.ui.theme.primaryContainerDark
import com.example.chatbot.ui.theme.primaryContainerDarkHighContrast
import com.example.chatbot.ui.theme.primaryContainerDarkMediumContrast
import com.example.chatbot.ui.theme.primaryContainerLight
import com.example.chatbot.ui.theme.primaryContainerLightHighContrast
import com.example.chatbot.ui.theme.primaryContainerLightMediumContrast
import com.example.chatbot.ui.theme.primaryDark
import com.example.chatbot.ui.theme.primaryDarkHighContrast
import com.example.chatbot.ui.theme.primaryDarkMediumContrast
import com.example.chatbot.ui.theme.primaryLight
import com.example.chatbot.ui.theme.primaryLightHighContrast
import com.example.chatbot.ui.theme.primaryLightMediumContrast
import com.example.chatbot.ui.theme.scrimDark
import com.example.chatbot.ui.theme.scrimDarkHighContrast
import com.example.chatbot.ui.theme.scrimDarkMediumContrast
import com.example.chatbot.ui.theme.scrimLight
import com.example.chatbot.ui.theme.scrimLightHighContrast
import com.example.chatbot.ui.theme.scrimLightMediumContrast
import com.example.chatbot.ui.theme.secondaryContainerDark
import com.example.chatbot.ui.theme.secondaryContainerDarkHighContrast
import com.example.chatbot.ui.theme.secondaryContainerDarkMediumContrast
import com.example.chatbot.ui.theme.secondaryContainerLight
import com.example.chatbot.ui.theme.secondaryContainerLightHighContrast
import com.example.chatbot.ui.theme.secondaryContainerLightMediumContrast
import com.example.chatbot.ui.theme.secondaryDark
import com.example.chatbot.ui.theme.secondaryDarkHighContrast
import com.example.chatbot.ui.theme.secondaryDarkMediumContrast
import com.example.chatbot.ui.theme.secondaryLight
import com.example.chatbot.ui.theme.secondaryLightHighContrast
import com.example.chatbot.ui.theme.secondaryLightMediumContrast
import com.example.chatbot.ui.theme.surfaceBrightDark
import com.example.chatbot.ui.theme.surfaceBrightDarkHighContrast
import com.example.chatbot.ui.theme.surfaceBrightDarkMediumContrast
import com.example.chatbot.ui.theme.surfaceBrightLight
import com.example.chatbot.ui.theme.surfaceBrightLightHighContrast
import com.example.chatbot.ui.theme.surfaceBrightLightMediumContrast
import com.example.chatbot.ui.theme.surfaceContainerDark
import com.example.chatbot.ui.theme.surfaceContainerDarkHighContrast
import com.example.chatbot.ui.theme.surfaceContainerDarkMediumContrast
import com.example.chatbot.ui.theme.surfaceContainerHighDark
import com.example.chatbot.ui.theme.surfaceContainerHighDarkHighContrast
import com.example.chatbot.ui.theme.surfaceContainerHighDarkMediumContrast
import com.example.chatbot.ui.theme.surfaceContainerHighLight
import com.example.chatbot.ui.theme.surfaceContainerHighLightHighContrast
import com.example.chatbot.ui.theme.surfaceContainerHighLightMediumContrast
import com.example.chatbot.ui.theme.surfaceContainerHighestDark
import com.example.chatbot.ui.theme.surfaceContainerHighestDarkHighContrast
import com.example.chatbot.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.example.chatbot.ui.theme.surfaceContainerHighestLight
import com.example.chatbot.ui.theme.surfaceContainerHighestLightHighContrast
import com.example.chatbot.ui.theme.surfaceContainerHighestLightMediumContrast
import com.example.chatbot.ui.theme.surfaceContainerLight
import com.example.chatbot.ui.theme.surfaceContainerLightHighContrast
import com.example.chatbot.ui.theme.surfaceContainerLightMediumContrast
import com.example.chatbot.ui.theme.surfaceContainerLowDark
import com.example.chatbot.ui.theme.surfaceContainerLowDarkHighContrast
import com.example.chatbot.ui.theme.surfaceContainerLowDarkMediumContrast
import com.example.chatbot.ui.theme.surfaceContainerLowLight
import com.example.chatbot.ui.theme.surfaceContainerLowLightHighContrast
import com.example.chatbot.ui.theme.surfaceContainerLowLightMediumContrast
import com.example.chatbot.ui.theme.surfaceContainerLowestDark
import com.example.chatbot.ui.theme.surfaceContainerLowestDarkHighContrast
import com.example.chatbot.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.example.chatbot.ui.theme.surfaceContainerLowestLight
import com.example.chatbot.ui.theme.surfaceContainerLowestLightHighContrast
import com.example.chatbot.ui.theme.surfaceContainerLowestLightMediumContrast
import com.example.chatbot.ui.theme.surfaceDark
import com.example.chatbot.ui.theme.surfaceDarkHighContrast
import com.example.chatbot.ui.theme.surfaceDarkMediumContrast
import com.example.chatbot.ui.theme.surfaceDimDark
import com.example.chatbot.ui.theme.surfaceDimDarkHighContrast
import com.example.chatbot.ui.theme.surfaceDimDarkMediumContrast
import com.example.chatbot.ui.theme.surfaceDimLight
import com.example.chatbot.ui.theme.surfaceDimLightHighContrast
import com.example.chatbot.ui.theme.surfaceDimLightMediumContrast
import com.example.chatbot.ui.theme.surfaceLight
import com.example.chatbot.ui.theme.surfaceLightHighContrast
import com.example.chatbot.ui.theme.surfaceLightMediumContrast
import com.example.chatbot.ui.theme.surfaceVariantDark
import com.example.chatbot.ui.theme.surfaceVariantDarkHighContrast
import com.example.chatbot.ui.theme.surfaceVariantDarkMediumContrast
import com.example.chatbot.ui.theme.surfaceVariantLight
import com.example.chatbot.ui.theme.surfaceVariantLightHighContrast
import com.example.chatbot.ui.theme.surfaceVariantLightMediumContrast
import com.example.chatbot.ui.theme.tertiaryContainerDark
import com.example.chatbot.ui.theme.tertiaryContainerDarkHighContrast
import com.example.chatbot.ui.theme.tertiaryContainerDarkMediumContrast
import com.example.chatbot.ui.theme.tertiaryContainerLight
import com.example.chatbot.ui.theme.tertiaryContainerLightHighContrast
import com.example.chatbot.ui.theme.tertiaryContainerLightMediumContrast
import com.example.chatbot.ui.theme.tertiaryDark
import com.example.chatbot.ui.theme.tertiaryDarkHighContrast
import com.example.chatbot.ui.theme.tertiaryDarkMediumContrast
import com.example.chatbot.ui.theme.tertiaryLight
import com.example.chatbot.ui.theme.tertiaryLightHighContrast
import com.example.chatbot.ui.theme.tertiaryLightMediumContrast

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun ChatbotTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkScheme
        else -> lightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}

