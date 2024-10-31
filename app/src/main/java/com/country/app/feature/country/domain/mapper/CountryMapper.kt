package com.country.app.feature.country.domain.mapper

import com.country.app.feature.country.domain.model.CountryData
import com.country.core.local.model.CountryEntity

interface CountryMapper {
    fun toCountryData(entity: CountryEntity): CountryData

}