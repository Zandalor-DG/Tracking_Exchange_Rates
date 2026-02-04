package com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.feature.currencies.CurrenciesScreen
import com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.feature.favorites.FavoritesScreen

@Composable
fun RootNavigationGraph(
  navController: NavHostController,
  startDestination: String = Destinations.Main.route,
) {
  NavHost(
    navController = navController,
    startDestination = startDestination
  ) {
    navigation(
      route = Destinations.Main.route,
      startDestination = Destinations.Main.Currencies.route
    ) {
      composable(Destinations.Main.Currencies.route) { backStackEntry ->
        CurrenciesScreen(
//          onNavigateToFilters = {
//            navController.navigate(Destinations.Filters.route)
//          },
        )
      }

      composable(Destinations.Main.Favorites.route) { backStackEntry ->
        FavoritesScreen()
      }
    }

//    composable(Destinations.Filters.route) { backStackEntry ->
//      FiltersScreen(
//        onNavigateBack = { navController.navigateUp() }
//      )
//    }
  }
}