package com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation

import androidx.compose.runtime.staticCompositionLocalOf

val LocalNavigationManager = staticCompositionLocalOf<NavigationManager> {
  error("No NavigationManager provided")
}