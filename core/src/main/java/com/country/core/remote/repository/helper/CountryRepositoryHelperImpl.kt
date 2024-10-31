package com.country.core.remote.repository.helper

import android.content.res.AssetManager
import com.country.core.di.qualifier.IoDispatcher
import com.country.core.local.model.CountryEntity
import com.country.core.remote.model.CountryDTO
import com.country.core.remote.repository.mapper.CountryMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

internal class CountryRepositoryHelperImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val json: Json,
    private val assetManager: AssetManager,
    private val mapper: CountryMapper
) : CountryRepositoryHelper {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getCountriesDTO(): List<CountryDTO> = withContext(ioDispatcher) {
        json.decodeFromStream(
            stream = assetManager.open("countries.json")
        )
    }

    override fun toCountries(list: List<CountryDTO>): List<CountryEntity> = list.map {
        mapper.toCountry(it)
    }
}