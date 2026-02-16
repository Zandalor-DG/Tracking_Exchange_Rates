package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation.Destinations
import com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation.LocalNavigationManager
import com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation.NavigationManagerImpl
import com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation.RootNavigationGraph
import com.paliy_dmitriy.tracking_exchange_rates.presentation.theme.TrackingExchangeRatesTheme
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  @Inject
  lateinit var navigationManager: NavigationManagerImpl

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      TrackingExchangeRatesTheme {

        CompositionLocalProvider(
          LocalNavigationManager provides navigationManager
        ) {
          RootNavigationGraph(
            startDestination = Destinations.Main.route,
            navigationManager = navigationManager
          )
        }
      }
    }

//    override fun onDestroy() {
//      super.onDestroy()
//      navigationManager.clearNavController()
//    }
  }
}