package com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation

import androidx.navigation.NavGraph

interface NavigationManager {
  suspend fun  navigateTo(destination: Destinations)
  suspend fun navigateUp()
  suspend fun popBackStack(destination: Destinations?, inclusive: Boolean)

  suspend fun setGraph(graph: NavGraph)
  suspend fun clearBackStack()
}