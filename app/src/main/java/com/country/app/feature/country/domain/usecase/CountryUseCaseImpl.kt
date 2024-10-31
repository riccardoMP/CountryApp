package com.country.app.feature.country.domain.usecase

import com.country.app.feature.country.domain.helper.CountryHelper
import com.country.app.feature.country.domain.model.CountryData
import com.country.app.feature.country.domain.usecase.state.CountryStateDomain
import com.country.app.feature.country.domain.usecase.state.CountryStateDomain.DataReady
import com.country.app.feature.country.domain.usecase.state.CountryStateDomain.Loading
import com.country.core.local.model.CountryEntity
import com.country.core.remote.repository.CountryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class CountryUseCaseImpl @Inject constructor(
    private val repository: CountryRepository,
    private val helper: CountryHelper,
) : CountryUseCase {

    private var fullCountryDataList: List<CountryData> = listOf()
    private var isDataLoaded = false


    override suspend fun filterData(query: String): Flow<CountryStateDomain> = flow {
        if (isDataLoaded) {
            val dataFiltered: List<CountryData> =
                helper.filterData(fullList = fullCountryDataList, query = query)
            emit(DataReady(dataFiltered))
        } else {
            emit(Loading)
            loadData()
            emit(DataReady(fullCountryDataList))
        }


    }.flowOn(Dispatchers.IO)

    suspend fun loadData() {
        val countryEntityList: List<CountryEntity> = repository.getCountries()
        fullCountryDataList = helper.loadData(list = countryEntityList)

        isDataLoaded = true // Mark data as loaded

    }
}