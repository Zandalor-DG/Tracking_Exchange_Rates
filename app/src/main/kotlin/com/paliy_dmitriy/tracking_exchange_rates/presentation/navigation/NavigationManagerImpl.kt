package com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class NavigationManagerImpl @Inject constructor() : NavigationManager {
  private var navController: NavController? = null
  private val _navigationCommands = Channel<NavigationCommand>()
  val navigationCommands = _navigationCommands.receiveAsFlow()

  fun setNavController(navController: NavController) {
    this.navController = navController
  }

  fun clearNavController() {
    this.navController = null
  }

  override suspend fun navigateTo(destination: Destinations) {
    _navigationCommands.send(
      NavigationCommand.NavigateToDestination(destination)
    )
  }

  override suspend fun navigateUp() {
    _navigationCommands.send(NavigationCommand.NavigateUp)
  }

  override suspend fun popBackStack(destination: Destinations?, inclusive: Boolean) {
    destination?.let {
      navController?.popBackStack(it.route, inclusive)
    }
  }

  override suspend fun setGraph(graph: NavGraph) {
    navController?.graph = graph
  }

  override suspend fun clearBackStack() {
    navController?.let { controller ->
      controller.popBackStack(controller.graph.startDestinationId, false)
    }
  }
}