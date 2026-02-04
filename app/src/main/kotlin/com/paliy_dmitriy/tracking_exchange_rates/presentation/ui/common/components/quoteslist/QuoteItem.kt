package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.quoteslist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.paliy_dmitriy.domain.model.quotes.QuoteModel

@Composable
fun QuoteItem(
  quote: QuoteModel,
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp)
  ) {
    Text(quote.title)
    Text(quote.price.toString())
  }
}