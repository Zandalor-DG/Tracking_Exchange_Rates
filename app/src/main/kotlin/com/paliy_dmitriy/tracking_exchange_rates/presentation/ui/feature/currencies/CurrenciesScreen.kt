package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.feature.currencies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
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

  Scaffold(
    snackbarHost = { SnackbarHost(snackbarHostState) }
  ) { paddingValues ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
    ) {
      when {
        isLoading.value -> {
          CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
          )
        }

        quotes.value.isNotEmpty() -> {
          QuotesList(
            quotes = quotes.value,
            onRefresh = viewModel::onRefresh,
            modifier = Modifier.fillMaxSize()
          )
        }

        else -> {
          EmptyState(
            onRetry = viewModel::retry,
            modifier = Modifier.align(Alignment.Center)
          )
        }
      }
    }
  }
}