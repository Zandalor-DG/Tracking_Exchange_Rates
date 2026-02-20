package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.customdropdown

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun DropdownMenu(
  options: List<String>,
  selectedOption: String,
  onOptionSelected: (String) -> Unit,
  onDismiss: () -> Unit,
  borderColor: Color,
  modifier: Modifier = Modifier
) {
  Popup(
    onDismissRequest = onDismiss,
    properties = PopupProperties(
      focusable = true,
      dismissOnBackPress = true,
      dismissOnClickOutside = true
    ),
  ) {
    Box(
      modifier = modifier
        .clip(RoundedCornerShape(8.dp))
        .background(
          color = MaterialTheme.colorScheme.background,
          shape = RoundedCornerShape(8.dp)
        )
        .border(
          border = BorderStroke(width = 1.dp, color = borderColor),
          shape = RoundedCornerShape(8.dp)
        )
        .wrapContentHeight()
    ) {
      Column {
        DropdownMenuItem(
          text = {
            Box(
              modifier = Modifier.fillMaxWidth(),
              contentAlignment = Alignment.CenterStart
            ) {
              Text(
                text = "USD", // Фиксированное значение для тестов без АПИ
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium,
              )
            }
          },
          modifier = Modifier.background(Color.Transparent),
          enabled = false,
          onClick = {},
          contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
        )

        LazyColumn(
          modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 152.dp)
        ) {
          items(options) { option ->
            DropdownMenuItem(
              text = {
                Text(
                  text = option,
                  color = if (option == selectedOption) {
                    MaterialTheme.colorScheme.onSecondary
                  } else {
                    MaterialTheme.colorScheme.onBackground
                  },
                  style = MaterialTheme.typography.bodyMedium,
                )
              },
              modifier = Modifier.background(
                if (option == selectedOption) {
                  MaterialTheme.colorScheme.secondary
                } else {
                  Color.Transparent
                }
              ),
              onClick = { onOptionSelected(option) },
              contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
            )
          }
        }
      }
    }
  }
}