package com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.compose.runtime.State
import javax.inject.Inject

class NavigationStateHolder @Inject constructor() {
  private val _currentDestination = MutableStateFlow<NavDestination?>(null)
  private var navController: NavController? = null

  val currentDestination: StateFlow<NavDestination?> = _currentDestination.asStateFlow()
  private var destinationListener: NavController.OnDestinationChangedListener? = null

  fun getCurrentDestination(): NavDestination? {
    return _currentDestination.value
  }

  fun setNavController(controller: NavController) {
    destinationListener?.let { oldListener ->
      navController?.removeOnDestinationChangedListener(oldListener)
    }
    navController = controller
    destinationListener = NavController.OnDestinationChangedListener { _, destination, _ ->
      _currentDestination.value = destination
    }
    destinationListener?.let { listener ->
      controller.addOnDestinationChangedListener(listener)
    }
    _currentDestination.value = controller.currentDestination
  }

  fun clearNavController() {
    destinationListener?.let { listener ->
      navController?.removeOnDestinationChangedListener(listener)
    }
    navController = null
    destinationListener = null
    _currentDestination.value = null
  }

  private fun setupDestinationListener() {
    navController?.addOnDestinationChangedListener { _, destination, _ ->
      _currentDestination.value = destination
    }
  }

  @Composable
  fun currentDestinationState(): State<NavDestination?> {
    return currentDestination.collectAsState()
  }
}