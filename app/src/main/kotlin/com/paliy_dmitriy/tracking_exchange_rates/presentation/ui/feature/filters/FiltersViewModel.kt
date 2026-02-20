package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.feature.filters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paliy_dmitriy.domain.model.QuoteItem
import com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.feature.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FiltersViewModel @Inject constructor(
) : BaseViewModel() {

    private val _sortOption = MutableStateFlow(SortOption.CODE_A_Z)
    val sortOption = _sortOption.asStateFlow()

    fun applySort(option: SortOption) {
        viewModelScope.launch {
            _sortOption.value = option
            // Здесь можно вызвать useCase для применения сортировки
            // Например: applySortingUseCase(option)
        }
    }

    fun sortQuotes(quotes: List<QuoteItem>, option: SortOption): List<QuoteItem> {
        return when (option) {
            SortOption.CODE_A_Z -> quotes.sortedBy { it.rateName }
            SortOption.CODE_Z_A -> quotes.sortedByDescending { it.rateName }
            SortOption.QUOTE_ASC -> quotes.sortedBy { it.rate }
            SortOption.QUOTE_DESC -> quotes.sortedByDescending { it.rate }
        }
    }
}