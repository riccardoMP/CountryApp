package com.country.core.remote.repository

import com.country.core.local.model.CountryEntity
import com.country.core.remote.model.CountryDTO
import com.country.core.remote.repository.helper.CountryRepositoryHelper
import javax.inject.Inject


internal class CountryRepositoryImpl @Inject constructor(
    private val helper: CountryRepositoryHelper
) : CountryRepository {

    override suspend fun getCountries(): List<CountryEntity> {
        val countryDTOList: List<CountryDTO> = helper.getCountriesDTO()
        return helper.toCountries(list = countryDTOList)
    }
}