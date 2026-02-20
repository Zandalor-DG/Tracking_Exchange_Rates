package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.customdropdown

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun DropdownInput(
  selectedOption: String,
  enabled: Boolean,
  onClick: () -> Unit,
  shape: RoundedCornerShape,
  borderColor: Color,
) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .clip(shape)
      .background(
        color = MaterialTheme.colorScheme.background,
        shape = shape
      )
      .border(
        border = BorderStroke(width = 1.dp, color = borderColor),
        shape = shape
      )
      .clickable(enabled = enabled, onClick = onClick)
      .padding(16.dp)
  ) {
    Box(
      modifier = Modifier.fillMaxWidth(),
      contentAlignment = Alignment.CenterStart
    ) {
      Text(
//        text = selectedOption.ifEmpty { "USD" },
        text = "USD",
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.bodyMedium,
      )
    }
  }
}