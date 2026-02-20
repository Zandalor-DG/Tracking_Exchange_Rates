package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.paliy_dmitriy.tracking_exchange_rates.R

@Composable
fun FiltersButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
) {
  Box(
    modifier = modifier
      .clip(
        RoundedCornerShape(8.dp)
      )
      .background(
        color = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(8.dp)
      )
      .border(
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.secondary),
        shape = RoundedCornerShape(8.dp)
      )
      .padding(16.dp)
      .clickable(enabled = enabled, onClick = onClick)
  ) {
    Icon(
      painter = painterResource(id = R.drawable.ic_filters),
      contentDescription = "filters-button",
      modifier = Modifier.size(24.dp),
      tint = MaterialTheme.colorScheme.secondary
    )
  }
}