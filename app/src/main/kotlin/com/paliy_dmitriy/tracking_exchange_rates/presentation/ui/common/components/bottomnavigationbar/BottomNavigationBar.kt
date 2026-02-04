package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.bottomnavigationbar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation.Destinations

@Composable
fun BottomNavigationBar(
  navController: NavController
) {
  val navBackStackEntry = navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry.value?.destination

  val shouldShowBottomNav = when (currentDestination?.route) {
    Destinations.Main.Currencies.route,
    Destinations.Main.Favorites.route -> true

    else -> false
  }

  if (!shouldShowBottomNav) return

  NavigationBar {
    val items = listOf(
      BottomNavItem.Currencies,
      BottomNavItem.Favorites,
    )

    items.forEach { item ->
      NavigationBarItem(
        selected = currentDestination?.hierarchy?.any {
          it.route == item.route
        } == true,
        onClick = {
          navController.navigate(item.route) {
            popUpTo(navController.graph.findStartDestination().id) {
              saveState = true
            }
            launchSingleTop = true
            restoreState = true
          }
        },
        icon = {
          Icon(
            painter = painterResource(id = item.icon),
            contentDescription = stringResource(id = item.title)
          )
        },
        label = { Text(stringResource(id = item.title)) }
      )
    }
  }
}