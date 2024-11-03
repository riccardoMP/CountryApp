package com.country.app.feature.country.domain.usecase.state

import com.country.app.feature.country.domain.model.CountryData


sealed interface CountryStateDomain {
    data class DataReady(val data: List<CountryData>) : CountryStateDomain
    data class DataError(val error: String) : CountryStateDomain
    data object Loading : CountryStateDomain
}