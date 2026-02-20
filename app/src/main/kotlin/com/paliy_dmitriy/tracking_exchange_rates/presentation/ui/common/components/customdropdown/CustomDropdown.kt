package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.customdropdown

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdown(
  options: List<String>,
  selectedOption: String,
  modifier: Modifier = Modifier,
  onOptionSelected: (String) -> Unit,
  enabled: Boolean = true,
) {
  var expanded by remember { mutableStateOf(false) }

  Box(
    modifier = modifier
  ) {
    DropdownInput(
      selectedOption = selectedOption,
      enabled = enabled,
      shape = RoundedCornerShape(8.dp),
      borderColor = MaterialTheme.colorScheme.secondary,
      onClick = { expanded = !expanded }
    )

    if (expanded) {
      DropdownMenu(
        options = options,
        selectedOption = selectedOption,
        onOptionSelected = { option ->
          onOptionSelected(option)
          expanded = false
        },
        onDismiss = { expanded = false },
        borderColor = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.matchParentSize(),
      )
    }
  }
}