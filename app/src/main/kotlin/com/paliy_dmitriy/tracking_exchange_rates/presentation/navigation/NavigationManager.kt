package com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import kotlinx.coroutines.flow.Flow

interface NavigationManager {
  val navigationCommands: Flow<NavigationCommand>

  @Composable
  fun currentDestinationState(): State<NavDestination?>

  fun setNavController(navController: NavController)
  fun clearNavController()

  suspend fun navigateTo(destination: Destinations)
  suspend fun navigateBottomNavTo(
    route: String,
    popUpToStart: Boolean = true
  )

  suspend fun navigateUp()
  suspend fun popBackStack(destination: Destinations?, inclusive: Boolean)
  suspend fun setGraph(graph: NavGraph)
  suspend fun clearBackStack()
}