package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.quotes_list

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun EmptyState(
  onRetry: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(text = "Нет данных")
    Button(onClick = onRetry) {
      Text("Загрузить снова")
    }
  }
}