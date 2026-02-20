package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Header(
  title: String = "",
  subElement: @Composable (() -> Unit)? = null
) {
  Column(
    Modifier
      .fillMaxWidth()
      .background(MaterialTheme.colorScheme.surface)
      .statusBarsPadding()
  ) {
    Text(
      text = title,
      style = MaterialTheme.typography.titleLarge,
      color = MaterialTheme.colorScheme.onBackground,
      modifier = Modifier.padding(
        top = 16.dp,
        start = 16.dp,
        end = 16.dp,
        bottom = 12.dp
      )
    )

    subElement?.invoke()

    HorizontalDivider(
      color = MaterialTheme.colorScheme.outline,
      thickness = 1.dp,
    )
  }
}