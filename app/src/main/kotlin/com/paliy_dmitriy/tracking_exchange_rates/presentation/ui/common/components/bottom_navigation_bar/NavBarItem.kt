package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.bottom_navigation_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun NavBarItem(
  isSelected: Boolean,
  icon: ImageVector,
  label: String,
  modifier: Modifier,
  onClick: () -> Unit
) {
  Box(
    modifier = modifier
      .fillMaxHeight(),
    contentAlignment = Alignment.TopCenter
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
      modifier = Modifier.padding(horizontal = 4.dp)
    ) {
      Spacer(modifier = Modifier.height(8.dp))
      if (isSelected) {
        Box(
          modifier = Modifier
            .width(64.dp)
            .height(32.dp)
            .background(
              color = MaterialTheme.colorScheme.primaryContainer,
              shape = CircleShape
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
      } else {
        Spacer(modifier = Modifier.height(4.dp))
        Icon(
          imageVector = icon,
          contentDescription = label,
          modifier = Modifier.size(24.dp),
          tint = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(8.dp))
      }

      Text(
        text = label,
        style = MaterialTheme.typography.labelSmall,
        color = if (isSelected) {
          MaterialTheme.colorScheme.onSurface
        } else {
          MaterialTheme.colorScheme.secondary
        }
      )
    }
  }
}