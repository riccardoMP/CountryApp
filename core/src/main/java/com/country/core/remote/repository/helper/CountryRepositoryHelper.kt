package com.country.core.remote.repository.helper

import com.country.core.local.model.CountryEntity
import com.country.core.remote.model.CountryDTO

internal interface CountryRepositoryHelper {

    suspend fun getCountriesDTO(): List<CountryDTO>

    fun toCountries(list: List<CountryDTO>): List<CountryEntity>

}