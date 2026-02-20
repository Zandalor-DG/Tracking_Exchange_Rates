package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.filters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation.LocalNavigationManager
import com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation.Destinations
import com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.buttons.FiltersButton
import com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.customdropdown.CustomDropdown
import kotlinx.coroutines.launch

@Composable
fun Filters(
  baseCurrency: String = "",
  symbols: List<String>,
) {
  val navigationManager = LocalNavigationManager.current
  val scope = rememberCoroutineScope()

  Box(modifier = Modifier.padding(16.dp, 8.dp, 16.dp, 12.dp)) {
    Row(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.fillMaxWidth()
    ) {

      CustomDropdown(
        options = symbols,
        selectedOption = baseCurrency.uppercase(),
        onOptionSelected = {},
        modifier = Modifier.weight(1f)
      )

      FiltersButton(
        modifier = Modifier.size(width = 48.dp, height = 48.dp),
        enabled = true,
        onClick = {
          scope.launch {
            navigationManager.navigateTo(Destinations.Filters)
          }
        }
      )
    }
  }
}