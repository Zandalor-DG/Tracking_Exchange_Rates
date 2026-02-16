package com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavGraph.Companion.findStartDestination
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManagerImpl @Inject constructor(
  private val navigationStateHolder: NavigationStateHolder
) : NavigationManager {
  private var navController: NavController? = null

  private val _navigationCommands = Channel<NavigationCommand>()
  override val navigationCommands: Flow<NavigationCommand> =
    _navigationCommands.receiveAsFlow()

  @Composable
  override fun currentDestinationState(): State<NavDestination?> {
    return navigationStateHolder.currentDestinationState()
  }

  override fun setNavController(navController: NavController) {
    this.navController = navController
    navigationStateHolder.setNavController(navController)
  }

  override fun clearNavController() {
    this.navController = null
    navigationStateHolder.clearNavController()
  }

  override suspend fun navigateTo(destination: Destinations) {
    _navigationCommands.send(
      NavigationCommand.NavigateToDestination(destination)
    )
  }

  override suspend fun navigateBottomNavTo(
    route: String,
    popUpToStart: Boolean
  ) {
    navController?.navigate(route) {
      if (popUpToStart) {
        popUpTo(navController?.graph?.findStartDestination()?.id ?: return@navigate) {
          saveState = true
        }
      }
      launchSingleTop = true
      restoreState = true
    }
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