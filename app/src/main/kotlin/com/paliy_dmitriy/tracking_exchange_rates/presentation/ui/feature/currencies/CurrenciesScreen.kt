package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.feature.currencies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.paliy_dmitriy.tracking_exchange_rates.R
import com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.quoteslist.EmptyState
import com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.quoteslist.QuotesList
import kotlinx.coroutines.launch

@Composable
fun CurrenciesScreen(
  viewModel: CurrenciesViewModel = hiltViewModel()
) {
  // Собираем состояния из ViewModel
  val isLoading = viewModel.isLoading.collectAsState()
  val quotes = viewModel.quotes.collectAsState()
  val error = viewModel.error.collectAsState()

  val snackbarHostState = remember { SnackbarHostState() }
  val scope = rememberCoroutineScope()

  LaunchedEffect(error.value) {
    error.value?.let { errorMessage ->
      scope.launch {
        snackbarHostState.showSnackbar(
          message = errorMessage,
          actionLabel = "Повторить"
        )

        viewModel.retry()
      }
    }
  }

  Column {

    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.surfaceVariant)
        .padding(16.dp)
    ) {
      Text(stringResource(R.string.currencies_title))
    }
    when {
      isLoading.value -> {
        CircularProgressIndicator(
          Modifier.fillMaxSize()
        )
      }

      quotes.value.isNotEmpty() -> {
        QuotesList(
          quotes.value,
          viewModel::onRefresh,
          Modifier.fillMaxSize()
        )
      }

      else -> {
        EmptyState(
          onRetry = viewModel::retry,
          Modifier.fillMaxSize()
        )
      }
    }
  }
}