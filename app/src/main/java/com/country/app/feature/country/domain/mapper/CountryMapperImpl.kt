package com.country.app.feature.country.domain.mapper

import com.country.app.feature.country.domain.model.CountryData
import com.country.core.local.model.CountryEntity
import javax.inject.Inject

class CountryMapperImpl @Inject constructor() : CountryMapper {
    override fun toCountryData(
        entity: CountryEntity
    ): CountryData = with(entity) {
        CountryData(
            country = "$city, $country",
            latitude = coordinate.latitude,
            longitude = coordinate.longitude
        )
    }
}