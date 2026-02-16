package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.bottom_navigation_bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation.Destinations
import com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation.LocalNavigationManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BottomNavigationBar(
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {
  val navigationManager = LocalNavigationManager.current
  val currentDestination = navigationManager.currentDestinationState()

  val shouldShowBottomNav = when (currentDestination.value?.route) {
    Destinations.Main.Currencies.route, Destinations.Main.Favorites.route -> true

    else -> false
  }

  if (!shouldShowBottomNav) return

  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .height(93.dp),
    color = MaterialTheme.colorScheme.background,
  ) {
    Column(
      Modifier.fillMaxWidth()
    ) {
      HorizontalDivider(
        color = MaterialTheme.colorScheme.outline,
        thickness = 1.dp,
      )

      Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Top
      ) {
        val items = listOf(
          BottomNavItem.Currencies,
          BottomNavItem.Favorites,
        )

        items.forEach { item ->
          NavBarItem(
            isSelected = currentDestination.value?.route == item.route,
            icon = ImageVector.vectorResource(id = item.icon),
            label = stringResource(id = item.title),
            modifier = Modifier.weight(1f),
            onClick = {
              coroutineScope.launch {
                navigationManager.navigateBottomNavTo(route = item.route, popUpToStart = true)
              }
            })
        }
      }
    }
  }
}