package com.country.core.remote.repository.mapper

import com.country.core.local.model.CountryEntity
import com.country.core.remote.model.CountryDTO
import javax.inject.Inject

internal class CountryMapperImpl @Inject constructor() : CountryMapper {
    override fun toCountry(dto: CountryDTO): CountryEntity = with(dto) {
        CountryEntity(
            id = id ?: 0,
            city = name.orEmpty(),
            country = country.orEmpty(),
            coordinate = CountryEntity.Coordinate(
                longitude = coordinate?.longitude ?: 0.0,
                latitude = coordinate?.latitude ?: 0.0
            )
        )
    }
}