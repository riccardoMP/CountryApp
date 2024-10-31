package com.country.core.remote.repository.mapper

import com.country.core.local.model.CountryEntity
import com.country.core.remote.model.CountryDTO

internal interface CountryMapper {
    fun toCountry(dto: CountryDTO): CountryEntity
}