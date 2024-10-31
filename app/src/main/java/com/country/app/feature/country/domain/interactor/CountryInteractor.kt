package com.country.app.feature.country.domain.interactor

import com.country.app.feature.country.domain.model.CountryData
import kotlinx.coroutines.flow.Flow

interface CountryInteractor {
    suspend fun loadData(): Flow<List<CountryData>>
    suspend fun filterData(currentList: List<CountryData>, query: String): Flow<List<CountryData>>
}