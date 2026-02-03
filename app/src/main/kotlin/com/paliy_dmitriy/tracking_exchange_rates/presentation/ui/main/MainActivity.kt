package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      To_DoTheme {
        val navController = rememberNavController()
        Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
          RootNavigationGraph(navController, innerPadding)
        }
      }
    }
  }
}