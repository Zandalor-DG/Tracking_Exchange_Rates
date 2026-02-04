package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.quoteslist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.paliy_dmitriy.domain.model.quotes.QuoteModel

@Composable
fun QuotesList(
  quotes: List<QuoteModel>,
  onRefresh: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier) {
    Button(
      onClick = onRefresh,
      modifier = Modifier.padding(16.dp)
    ) {
      Text("Обновить")
    }

    LazyColumn {
      items(quotes, key = { it.id }) { quote ->
        QuoteItem(quote)
      }
    }
  }
}