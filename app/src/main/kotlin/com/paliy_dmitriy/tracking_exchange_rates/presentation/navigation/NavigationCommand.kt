package com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation

import androidx.navigation.NavGraph
import androidx.navigation.NavOptions

sealed class NavigationCommand {
  data class NavigateToRoute(
    val route: String,
    val navOptions: NavOptions? = null
  ) : NavigationCommand()

  data class NavigateToDestination(
    val destination: Destinations,
    val navOptions: NavOptions? = null
  ) : NavigationCommand()

  object NavigateUp : NavigationCommand()

  data class PopBackStack(
    val destination: Destinations? = null,
    val inclusive: Boolean = false
  ) : NavigationCommand()

  data class SetGraph(
    val graph: NavGraph
  ) : NavigationCommand()
}