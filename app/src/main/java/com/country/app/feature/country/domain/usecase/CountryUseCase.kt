package com.country.app.feature.country.domain.usecase

import com.country.app.feature.country.domain.usecase.state.CountryStateDomain
import kotlinx.coroutines.flow.Flow

interface CountryUseCase {
    //suspend fun filterData(currentList: List<CountryData>, query: String): Flow<List<CountryData>>
    suspend fun filterData(query: String): Flow<CountryStateDomain>
}