package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.feature.currencies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
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
import com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.bottom_navigation_bar.BottomNavigationBar
import com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.header.Header
import com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.quotes_list.EmptyState
import com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.quotes_list.QuotesList
import kotlinx.coroutines.launch

@Composable
fun CurrenciesScreen(
  viewModel: CurrenciesViewModel = hiltViewModel(),
) {
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
          actionLabel = "Повторить",
          duration = SnackbarDuration.Long
        )
      }
    }
  }

  Scaffold(
    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    topBar = {
      Header()
    },
    contentColor = MaterialTheme.colorScheme.onBackground,
    bottomBar = { BottomNavigationBar() }
  ) { paddingValues ->
    Column(Modifier.padding(paddingValues)) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(MaterialTheme.colorScheme.background)
      ) {
        when {
          isLoading.value -> {
            Box(
              modifier = Modifier.fillMaxSize(),
              contentAlignment = Alignment.Center
            ) {
              CircularProgressIndicator()
            }
          }

          quotes.value.quoteList.isNotEmpty() -> {
            QuotesList(
              quotes = quotes.value.quoteList,
//              onRefresh = viewModel::onRefresh,
//              onRefresh = () -> { },
              modifier = Modifier.fillMaxSize()
            )
          }

          else -> {
            EmptyState(
              onRetry = viewModel::retry,
              modifier = Modifier.fillMaxSize()
            )
          }
        }
      }
    }
  }
}