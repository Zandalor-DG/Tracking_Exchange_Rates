package com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.feature.currencies.CurrenciesScreen
import com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.feature.favorites.FavoritesScreen

@Composable
fun MainNavigationGraph(
  navController: NavHostController
) {
  NavHost(
    navController = navController,
    startDestination = Destinations.Main.Currencies.route,
    route = Destinations.Main.route
  ) {
    composable(route = Destinations.Main.Currencies.route) {
      CurrenciesScreen()
    }

    composable(route = Destinations.Main.Favorites.route) {
      FavoritesScreen()
    }
  }
}