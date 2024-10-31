package com.country.app.feature.country.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.country.app.feature.country.ui.CountryListScreen
import com.country.app.feature.country.viewmodel.CountryViewModel
@Composable
fun CountryScreen(
    viewModel: CountryViewModel,
) {
    CountryListScreen (
        viewModel = viewModel,
    )
}
