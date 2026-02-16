package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.quotes_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.paliy_dmitriy.domain.model.QuoteItem

@Composable
fun QuotesList(
  quotes: List<QuoteItem>,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    verticalArrangement = Arrangement.spacedBy(8.dp),
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {
    items(quotes, key = { it.rateName + it.rate }) { quote ->
      QuoteItem(
        quote = quote,
        onFavoriteClick = { /* добавить в избранное */ }
      )
    }
  }

}