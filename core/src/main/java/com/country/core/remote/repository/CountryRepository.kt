package com.country.core.remote.repository

import com.country.core.local.model.CountryEntity

interface CountryRepository {
    suspend fun getCountries(): List<CountryEntity>
}