package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.quotes_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.paliy_dmitriy.domain.model.QuoteItem
import com.paliy_dmitriy.tracking_exchange_rates.R

@Composable
fun QuoteItem(
  quote: QuoteItem,
  onFavoriteClick: (() -> Unit)? = null
) {

  Card(
    modifier = Modifier
      .fillMaxWidth(),
    shape = MaterialTheme.shapes.medium,
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.surfaceVariant,
      contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    ),
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Min)
        .padding(horizontal = 16.dp, vertical = 12.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      // Название котировки
      Text(
        text = quote.rateName,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.weight(1f)
      )

      Spacer(modifier = Modifier.width(8.dp))

      // Цена и иконка
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Text(
          text = quote.rate.toString(),
          style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.SemiBold
          ),
          color = MaterialTheme.colorScheme.onSurface
        )

        IconButton(
          onClick = { onFavoriteClick?.invoke() },
          modifier = Modifier.size(24.dp),
          enabled = onFavoriteClick != null
        ) {
          Icon(
            imageVector = if (quote.isFavorite) {
              ImageVector.vectorResource(id = R.drawable.ic_favorites)
            } else {
              ImageVector.vectorResource(
                id = R.drawable.ic_favorites_off
              )
            },

            contentDescription = stringResource(R.string.currencies_add_quote_to_favorites),

            tint = if (quote.isFavorite) {
              MaterialTheme.colorScheme.tertiary
            } else {
              MaterialTheme.colorScheme.secondary
            },
          )
        }
      }
    }
  }
}