package com.country.app.feature.country.domain.helper

import com.country.app.feature.country.domain.model.CountryData
import com.country.core.local.model.CountryEntity


interface CountryHelper {
    suspend fun toCountryDataList(list: List<CountryEntity>): List<CountryData>

    suspend fun toCountryData(country: CountryEntity): CountryData

    fun filterData(
        fullList: List<CountryData>,
        filteredList: List<CountryData>,
        query: String
    ): List<CountryData>
}