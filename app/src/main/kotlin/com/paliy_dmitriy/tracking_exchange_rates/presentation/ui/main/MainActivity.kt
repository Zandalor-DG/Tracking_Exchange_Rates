package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation.Destinations
import com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation.NavigationCommand
import com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation.NavigationManagerImpl
import com.paliy_dmitriy.tracking_exchange_rates.presentation.theme.TrackingExchangeRatesTheme
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  @Inject
  lateinit var navigationManager: NavigationManagerImpl

  private lateinit var navController: NavHostController

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      TrackingExchangeRatesTheme {
        navController = rememberNavController()

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

        MainApp(navController)
      }
    }

//    override fun onDestroy() {
//      super.onDestroy()
//      navigationManager.clearNavController()
//    }
  }
}