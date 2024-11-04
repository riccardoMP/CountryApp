package com.country.app.usecase


import com.country.app.feature.country.domain.helper.CountryHelperImpl
import com.country.app.feature.country.domain.usecase.CountryUseCaseImpl
import com.country.core.remote.repository.CountryRepository
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CountryUseCaseTest {

    @Mock
    private lateinit var useCase: CountryUseCaseImpl

    @Mock
    private lateinit var repository: CountryRepository

    @Mock
    private lateinit var helper: CountryHelperImpl


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = CountryUseCaseImpl(repository = repository, helper = helper)
    }


}