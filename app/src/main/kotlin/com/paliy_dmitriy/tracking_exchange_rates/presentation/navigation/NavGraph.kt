package com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.feature.currencies.CurrenciesScreen
import com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.feature.favorites.FavoritesScreen

@Composable
fun RootNavigationGraph(
  startDestination: String = Destinations.Main.route,
  navigationManager: NavigationManager,
  navController: NavHostController = rememberNavController()
) {
//  val navigationManager = LocalNavigationManager.current
//  val navController = rememberNavController()

  LaunchedEffect(Unit) {
    navigationManager.setNavController(navController)
  }

  LaunchedEffect(Unit) {
    navigationManager.navigationCommands.collect { command ->
      when (command) {
        is NavigationCommand.NavigateToDestination -> {
          val navOptions = command.navOptions ?: NavOptions.Builder()
            .setLaunchSingleTop(true)
            .build()

          navController.navigate(
            command.destination.route,
            navOptions
          )
        }

        is NavigationCommand.NavigateToRoute -> {
          val navOptions = command.navOptions ?: NavOptions.Builder()
            .setLaunchSingleTop(true)
            .build()

          navController.navigate(
            command.route,
            navOptions
          )
        }

        is NavigationCommand.NavigateUp -> {
          val navigatedUp = navController.navigateUp()

          if (!navigatedUp) {
            // Можно закрыть Activity или обработать иначе
            // Например, показать диалог выхода
            if (navController.currentBackStackEntry?.destination?.route
              == Destinations.Main.Currencies.route
            ) {
              // Мы на домашнем экране, может быть закрыть приложение?
              // activity.finish() или показать диалог
            }
          }
        }

        is NavigationCommand.PopBackStack -> {
          when {
            command.destination != null -> {
              navController.popBackStack(
                route = command.destination.route,
                inclusive = command.inclusive,
              )
            }

            else -> {
              navController.popBackStack()
            }
          }
        }

        is NavigationCommand.SetGraph -> navController.graph = command.graph
      }
    }
  }

  NavHost(
    navController = navController,
    startDestination = startDestination,
  ) {
    navigation(
      route = Destinations.Main.route,
      startDestination = Destinations.Main.Currencies.route
    ) {
      composable(Destinations.Main.Currencies.route) { backStackEntry ->
        CurrenciesScreen()
      }

      composable(Destinations.Main.Favorites.route) { backStackEntry ->
        FavoritesScreen()
      }
    }

//    composable(Destinations.Filters.route) { backStackEntry ->
//      FiltersScreen(navigationManager = navigationManager)
//    }
  }
}