package com.country.app.feature.country.domain.helper

import com.country.app.feature.country.domain.mapper.CountryMapper
import com.country.app.feature.country.domain.model.CountryData
import com.country.app.feature.country.domain.usecase.state.CountryStateDomain
import com.country.app.feature.country.domain.usecase.state.CountryStateDomain.DataError
import com.country.app.feature.country.domain.usecase.state.CountryStateDomain.DataReady
import com.country.core.local.model.CountryEntity
import javax.inject.Inject

class CountryHelperImpl @Inject constructor(
    private val mapper: CountryMapper
) : CountryHelper {
    override suspend fun loadData(list: List<CountryEntity>): List<CountryData> {
        return toCountryDataList(list)
    }


    fun toCountryDataList(list: List<CountryEntity>): List<CountryData> {
        return list
            .sortedWith(compareBy<CountryEntity> { it.city }.thenBy { it.country })
            .map { toCountryData(it) }
    }

    fun toCountryData(country: CountryEntity): CountryData =
        mapper.toCountryData(country)

    override fun filterData(fullList: List<CountryData>, query: String): List<CountryData> {
        return if (query.isEmpty()) {
            fullList
        } else {
            fullList.filter {
                it.country.startsWith(query, ignoreCase = true)
            }
        }
    }
}