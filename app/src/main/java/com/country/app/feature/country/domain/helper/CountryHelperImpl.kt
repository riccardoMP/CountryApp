package com.country.app.feature.country.domain.helper

import com.country.app.feature.country.domain.mapper.CountryMapper
import com.country.app.feature.country.domain.model.CountryData
import com.country.core.local.model.CountryEntity
import javax.inject.Inject

class CountryHelperImpl @Inject constructor(
    private val mapper: CountryMapper
) : CountryHelper {
    override suspend fun toCountryDataList(list: List<CountryEntity>): List<CountryData> {
        return list
            .sortedWith(compareBy<CountryEntity> { it.city }.thenBy { it.country })
            .map { toCountryData(it) }
    }


    override suspend fun toCountryData(country: CountryEntity): CountryData =
        mapper.toCountryData(country)

    override fun filterData(
        fullList: List<CountryData>,
        filteredList: List<CountryData>, query: String
    ): List<CountryData> {
        return if (query.isEmpty()) {
            fullList
        } else {
            filteredList.filter {
                it.country.startsWith(query, ignoreCase = true)
            }
        }
    }

    fun sortCountries(countries: List<CountryEntity>): List<CountryEntity> {
        return countries.sortedWith(compareBy<CountryEntity> { it.city }.thenBy { it.country })

    }
}