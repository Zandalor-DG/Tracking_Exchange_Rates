package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.bottom_navigation_bar

import com.paliy_dmitriy.tracking_exchange_rates.R
import com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation.Destinations

sealed class BottomNavItem(
  val route: String,
  val title: Int,
  val icon: Int
) {
  object Currencies : BottomNavItem(
    route = Destinations.Main.Currencies.route,
    title = R.string.currencies_title,
    icon = R.drawable.ic_currencies
  )

  object Favorites : BottomNavItem(
    route = Destinations.Main.Favorites.route,
    title = R.string.favorites_title,
    icon = R.drawable.ic_favorites
  )
}