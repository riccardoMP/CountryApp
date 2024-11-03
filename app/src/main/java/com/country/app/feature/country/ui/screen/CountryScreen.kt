package com.country.app.feature.country.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.navigation.NavHostController
import com.country.app.feature.country.domain.model.CountryData
import com.country.app.feature.country.viewmodel.state.CountryUIState
import com.country.app.feature.country.viewmodel.state.CountryUIState.OnDataError
import com.country.app.feature.country.viewmodel.state.CountryUIState.OnDataReady
import com.country.app.feature.country.viewmodel.state.CountryUIState.OnLoading
import com.country.app.util.Screen

@Composable
fun CountryScreen(
    navHostController: NavHostController,
    uiState: State<CountryUIState>,
    onQueryUpdated: (String) -> Unit
) {

    val onCountryClick: (Int) -> Unit = { countryId ->
        val route: String = Screen.DetailsScreen.passId(countryId)
        navHostController.navigate(route)
    }

    when (val response = uiState.value) {
        is OnDataReady -> CountryListScreen(
            list = response.data,
            onQueryUpdated = onQueryUpdated,
            onCountryClick = onCountryClick
        )

        is OnDataError -> ErrorScreen(errorMessage = response.error)
        is OnLoading -> LoadingScreen()
    }

}
