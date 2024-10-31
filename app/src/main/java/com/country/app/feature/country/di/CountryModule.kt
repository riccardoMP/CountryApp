package com.country.app.feature.country.di

import com.country.app.feature.country.domain.helper.CountryHelper
import com.country.app.feature.country.domain.helper.CountryHelperImpl
import com.country.app.feature.country.domain.interactor.CountryInteractor
import com.country.app.feature.country.domain.interactor.CountryInteractorImpl
import com.country.app.feature.country.domain.mapper.CountryMapper
import com.country.app.feature.country.domain.mapper.CountryMapperImpl
import com.country.core.remote.repository.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object CountryModule {

    @Provides
    fun bindCountryMapper(): CountryMapper =
        CountryMapperImpl()

    @Provides
    fun bindCountryHelper(
        mapper: CountryMapper
    ): CountryHelper =
        CountryHelperImpl(mapper = mapper)

    @Provides
    fun bindCountryInteractor(
        repository: CountryRepository,
        helper: CountryHelper
    ): CountryInteractor =
        CountryInteractorImpl(repository = repository, helper = helper)
}