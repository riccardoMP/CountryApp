package com.country.app.feature.country.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.country.app.feature.country.ui.CountryListScreen
import com.country.app.feature.country.viewmodel.CountryViewModel
import com.country.app.feature.country.viewmodel.state.CountryUIState
import com.country.app.feature.country.viewmodel.state.CountryUIState.OnDataError
import com.country.app.feature.country.viewmodel.state.CountryUIState.OnDataReady
import com.country.app.feature.country.viewmodel.state.CountryUIState.OnLoading

@Composable
fun CountryScreen(
    uiState: State<CountryUIState>,
    onQueryUpdated: (String) -> Unit
) {

    when (val response = uiState.value) {
        is OnDataReady -> CountryListScreen(list = response.data, onQueryUpdated = onQueryUpdated)
        is OnDataError -> ErrorScreen(errorMessage = response.error)
        is OnLoading -> LoadingScreen()
    }

}
