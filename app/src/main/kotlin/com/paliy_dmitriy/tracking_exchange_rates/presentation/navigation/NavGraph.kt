package com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RootNavigationGraph(
  navController: NavHostController,
  innerPadding: PaddingValues,
  startDestination: String = Destinations.Currencies.route,
) {
  NavHost(
    navController = navController,
    startDestination = startDestination
  ) {
    composable(Destinations.Currencies.route) {
      HomeScreen(innerPadding)
    }
  }
}