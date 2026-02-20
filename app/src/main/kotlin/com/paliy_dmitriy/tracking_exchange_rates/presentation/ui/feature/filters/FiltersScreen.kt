package com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.feature.filters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.paliy_dmitriy.tracking_exchange_rates.presentation.ui.common.components.header.Header

enum class SortOption(val displayName: String) {
    CODE_A_Z("Code A-Z"),
    CODE_Z_A("Code Z-A"),
    QUOTE_ASC("Quote Asc."),
    QUOTE_DESC("Quote Desc.")
}

@Composable
fun FiltersScreen(
    viewModel: FiltersViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    var selectedSort by remember { mutableStateOf(SortOption.CODE_A_Z) }

    Scaffold(
        topBar = {
            Header(
                title = "Filters",
                subElement = {
                    // Время (9:30) из PDF
                    Text(
                        text = "9:30",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
            // Заголовок секции сортировки
            Text(
                text = "SORT BY",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(16.dp)
            )

            // Список опций сортировки
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(SortOption.values()) { option ->
                    SortOptionItem(
                        option = option,
                        isSelected = selectedSort == option,
                        onSelect = { selectedSort = option }
                    )
                }
            }

            // Кнопки Apply и Cancel
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onNavigateBack,
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    )
                ) {
                    Text(
                        text = "Cancel",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Button(
                    onClick = {
                        // Применить фильтры
                        viewModel.applySort(selectedSort)
                        onNavigateBack()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(
                        text = "Apply",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
fun SortOptionItem(
    option: SortOption,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = option.displayName,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            RadioButton(
                selected = isSelected,
                onClick = onSelect,
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.primary
                )
            )
        }

        HorizontalDivider(
            color = MaterialTheme.colorScheme.outline,
            thickness = 1.dp
        )
    }
}