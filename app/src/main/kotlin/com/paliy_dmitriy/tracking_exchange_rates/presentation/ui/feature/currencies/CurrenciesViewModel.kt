package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.feature.currencies

import androidx.lifecycle.ViewModel
import com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
  private val navigationManager: NavigationManager,
//  private val loadToDoItemsUseCase: LoadToDoItemsUseCase
) : ViewModel() {

}