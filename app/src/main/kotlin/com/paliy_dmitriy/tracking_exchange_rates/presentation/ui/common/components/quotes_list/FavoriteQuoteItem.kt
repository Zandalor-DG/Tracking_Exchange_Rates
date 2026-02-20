package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.quotes_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column  // Добавить этот импорт
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
import com.paliy_dmitriy.domain.model.FavoriteQuote
import com.paliy_dmitriy.tracking_exchange_rates.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun FavoriteQuoteItem(
  favorite: FavoriteQuote,
  onRemoveClick: () -> Unit
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
      Column(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.spacedBy(4.dp)
      ) {
        Text(
          text = favorite.customName ?: favorite.quoteId,
          style = MaterialTheme.typography.bodyMedium,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          fontWeight = FontWeight.SemiBold
        )

        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        Text(
          text = dateFormat.format(Date(favorite.addedDate)),
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }

      Spacer(modifier = Modifier.width(8.dp))

      IconButton(
        onClick = onRemoveClick,
        modifier = Modifier.size(24.dp)
      ) {
        Icon(
          imageVector = ImageVector.vectorResource(id = R.drawable.ic_favorites_off),
          contentDescription = stringResource(R.string.remove_from_favorites),
          tint = MaterialTheme.colorScheme.secondary,
        )
      }
    }
  }
}