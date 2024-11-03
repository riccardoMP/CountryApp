package com.country.app.feature.country.viewmodel.state

import com.country.app.feature.country.domain.model.CountryData


sealed class CountryUIState {

    data class OnDataReady(val data: List<CountryData>) : CountryUIState()
    data class OnDataError(val error: String) : CountryUIState()
    data object OnLoading : CountryUIState()
}