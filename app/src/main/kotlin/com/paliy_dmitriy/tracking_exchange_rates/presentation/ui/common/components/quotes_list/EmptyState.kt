package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.quotes_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.paliy_dmitriy.tracking_exchange_rates.R

@Composable
fun EmptyState(
  message: String = stringResource(R.string.no_data),
  onRetry: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Text(
      text = message,
      style = MaterialTheme.typography.bodyMedium,
      color = MaterialTheme.colorScheme.onSurfaceVariant
    )

    Spacer(modifier = Modifier.height(16.dp))

    Button(
      onClick = onRetry,
      shape = MaterialTheme.shapes.medium
    ) {
      Text(stringResource(R.string.retry))
    }
  }
}