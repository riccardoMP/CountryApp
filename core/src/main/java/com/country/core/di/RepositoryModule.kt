package com.country.core.di

import android.content.res.AssetManager
import com.country.core.di.qualifier.IoDispatcher
import com.country.core.remote.repository.CountryRepository
import com.country.core.remote.repository.CountryRepositoryImpl
import com.country.core.remote.repository.helper.CountryRepositoryHelper
import com.country.core.remote.repository.helper.CountryRepositoryHelperImpl
import com.country.core.remote.repository.mapper.CountryMapper
import com.country.core.remote.repository.mapper.CountryMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class RepositoryModule {

    @Provides
    fun provideCountryMapper(): CountryMapper = CountryMapperImpl()

    @Provides
    @Singleton
    fun provideCountryHelper(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        json: Json,
        assetManager: AssetManager,
        mapper: CountryMapper
    ): CountryRepositoryHelper = CountryRepositoryHelperImpl(
        mapper = mapper,
        json = json,
        assetManager = assetManager,
        ioDispatcher = ioDispatcher
    )


    @Provides
    fun provideCountryRepository(helper: CountryRepositoryHelper): CountryRepository =
        CountryRepositoryImpl(helper = helper)

}