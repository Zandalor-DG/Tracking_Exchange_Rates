package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation.Destinations
import com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation.RootNavigationGraph
import com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.bottomnavigationbar.BottomNavigationBar

@Composable
fun MainApp(
  navController: NavHostController,
) {
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = navBackStackEntry?.destination?.route

  // Определяем, нужно ли показывать Bottom Navigation
  val showBottomNav = when (currentRoute) {
    Destinations.Main.Currencies.route,
    Destinations.Main.Favorites.route -> true
    else -> false
  }

  Scaffold(
    bottomBar = {
      if (showBottomNav) {
        BottomNavigationBar(navController = navController)
      }
    }
  ) { paddingValues ->
    RootNavigationGraph(
      navController = navController,
      startDestination = Destinations.Main.route,
      modifier = Modifier.padding(paddingValues)
    )
  }
}