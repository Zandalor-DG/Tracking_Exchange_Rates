package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.feature.favorites

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.paliy_dmitriy.tracking_exchange_rates.R
import com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.bottom_navigation_bar.BottomNavigationBar
import com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.header.Header
import com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.quotes_list.EmptyState
import com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.quotes_list.FavoriteQuotesList
import kotlinx.coroutines.launch

@Composable
fun FavoritesScreen(
  viewModel: FavoriteViewModel = hiltViewModel(),
) {
  val isLoading by viewModel.isLoading.collectAsState()
  val favorites by viewModel.favorites.collectAsState()
  val error by viewModel.error.collectAsState()

  val snackbarHostState = remember { SnackbarHostState() }
  val scope = rememberCoroutineScope()
  val context = LocalContext.current

  LaunchedEffect(error) {
    error?.let { errorMessage ->
      scope.launch {
        snackbarHostState.showSnackbar(
          message = errorMessage,
          actionLabel = context.getString(R.string.retry),
          duration = SnackbarDuration.Long
        )
      }
    }
  }

  Scaffold(
    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    topBar = {
      Header(
        title = stringResource(R.string.favorites_title)
      )
    },
    contentColor = MaterialTheme.colorScheme.onBackground,
    bottomBar = { BottomNavigationBar() }
  ) { paddingValues ->
    Column(
      modifier = Modifier
        .padding(paddingValues)
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ) {
      when {
        isLoading -> {
          Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
          ) {
            CircularProgressIndicator()
          }
        }

        favorites.isNotEmpty() -> {
          FavoriteQuotesList(
            favorites = favorites,
            onRemoveClick = { quoteId ->
              viewModel.removeFromFavorites(quoteId)
            },
            modifier = Modifier.fillMaxSize()
          )
        }

        else -> {
          EmptyState(
            message = stringResource(R.string.no_favorites),
            onRetry = { viewModel.retry() },
            modifier = Modifier.fillMaxSize()
          )
        }
      }
    }
  }
}