package com.country.app.feature.country.domain.interactor

import com.country.app.feature.country.domain.helper.CountryHelper
import com.country.app.feature.country.domain.model.CountryData
import com.country.core.local.model.CountryEntity
import com.country.core.remote.repository.CountryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class CountryInteractorImpl @Inject constructor(
    private val repository: CountryRepository,
    private val helper: CountryHelper,
) : CountryInteractor {

    private var fullCountryDataList: List<CountryData> = listOf()

    override suspend fun loadData(): Flow<List<CountryData>> =
        flow {

            val countryList: List<CountryEntity> = repository.getCountries()
            fullCountryDataList = helper.toCountryDataList(countryList)

            emit(fullCountryDataList)

        }.flowOn(Dispatchers.IO)

    override suspend fun filterData(
        currentList: List<CountryData>,
        query: String
    ): Flow<List<CountryData>> =
        flow {

            val dataFiltered: List<CountryData> =
                helper.filterData(
                    fullList = fullCountryDataList,
                    filteredList = currentList,
                    query = query
                )

            emit(dataFiltered)

        }.flowOn(Dispatchers.IO)


}