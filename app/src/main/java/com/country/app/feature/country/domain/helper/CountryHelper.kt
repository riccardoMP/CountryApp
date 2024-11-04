package com.country.app.feature.country.domain.helper

import com.country.app.feature.country.domain.model.CountryData
import com.country.core.local.model.CountryEntity


interface CountryHelper {
    suspend fun loadData(list: List<CountryEntity>): List<CountryData>

    fun filterData(
        fullList: List<CountryData>,
        query: String
    ): List<CountryData>
}