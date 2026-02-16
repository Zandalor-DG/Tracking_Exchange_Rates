package com.paliy_dmitriy.tracking_exchange_rates.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private val LightColorScheme = lightColorScheme(
  primary = Primary,
  onPrimary = OnPrimary,
  primaryContainer = LightPrimary,
  onPrimaryContainer = Primary,

  secondary = Secondary,
  onSecondary = OnPrimary,
  secondaryContainer = LightPrimary.copy(alpha = 0.5f),
  onSecondaryContainer = Primary,

  tertiary = Yellow,
  onTertiary = TextDefault,
  tertiaryContainer = Yellow.copy(alpha = 0.2f),
  onTertiaryContainer = TextDefault,

  background = Default,
  onBackground = TextDefault,

  surface = Header,
  onSurface = TextDefault,
  surfaceVariant = Card,
  onSurfaceVariant = TextSecondary,
  surfaceTint = Primary,

  outline = Outline,
  outlineVariant = Outline.copy(alpha = 0.5f),
)

private val DarkColorScheme = darkColorScheme(
  primary = DarkPrimary,
  onPrimary = DarkOnPrimary,
  primaryContainer = DarkLightPrimary,
  onPrimaryContainer = DarkPrimary,

  secondary = DarkSecondary,
  onSecondary = DarkOnPrimary,
  secondaryContainer = DarkLightPrimary.copy(alpha = 0.3f),
  onSecondaryContainer = DarkSecondary,

  tertiary = DarkYellow,
  onTertiary = DarkTextDefault,
  tertiaryContainer = DarkYellow.copy(alpha = 0.2f),
  onTertiaryContainer = DarkYellow,

  background = DarkDefault,
  onBackground = DarkTextDefault,

  surface = DarkHeader,
  onSurface = DarkTextDefault,
  surfaceVariant = DarkCard,
  onSurfaceVariant = DarkTextSecondary,
  surfaceTint = DarkPrimary,

  outline = DarkOutline,
  outlineVariant = DarkOutline.copy(alpha = 0.5f),
)

@Composable
fun TrackingExchangeRatesTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Dynamic color is available on Android 12+
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit
) {
  val colorScheme = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
      val context = LocalContext.current
      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }

    darkTheme -> DarkColorScheme
    else -> LightColorScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    shapes = Shapes(
      small = RoundedCornerShape(8.dp),
      medium = RoundedCornerShape(12.dp),
      large = RoundedCornerShape(16.dp)
    ),
    content = content
  )
}