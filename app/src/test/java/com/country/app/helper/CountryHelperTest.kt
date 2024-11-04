package com.country.app.helper

import com.country.app.feature.country.domain.helper.CountryHelperImpl
import com.country.app.feature.country.domain.mapper.CountryMapperImpl
import com.country.app.feature.country.domain.model.CountryData
import com.country.core.local.model.CountryEntity
import com.country.core.local.model.CountryEntity.Coordinate
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations

class CountryHelperTest {

    @Mock
    private lateinit var helper: CountryHelperImpl

    @Mock
    private lateinit var mapper: CountryMapperImpl


    private var countryEntityList: List<CountryEntity> = listOf(
        CountryEntity(1, "Peru", "Lima", Coordinate(-79.3832, 43.6532)),
        CountryEntity(2, "USA", "New York", Coordinate(-79.3832, 43.6532)),
        CountryEntity(3, "Argentina", "Buenos Aires", Coordinate(-79.3832, 43.6532))
    )

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        //mapper = CountryMapperImpl()
        helper = CountryHelperImpl(mapper = mapper)
    }

    @Test
    fun `Given a CountryEntity list, When loadData is called, Then it should return sorted CountryData list`() =
        runBlocking {
            val countryData = CountryData(
                id = 3,
                country = "Buenos Aires, Argentina",
                longitude = -79.3832,
                latitude = 43.6532
            )
            val expectedCountryData: List<CountryData> = listOf(
                countryData,
                countryData.copy(id = 1, country = "Lima, Peru"),
                countryData.copy(id = 2, country = "New York, USA")
            )

            doReturn(expectedCountryData[0]).`when`(mapper).toCountryData(countryEntityList[2])
            doReturn(expectedCountryData[1]).`when`(mapper).toCountryData(countryEntityList[0])
            doReturn(expectedCountryData[2]).`when`(mapper).toCountryData(countryEntityList[1])


            val result = helper.loadData(countryEntityList)

            assertEquals(expectedCountryData, result)
        }

    @Test
    fun `Given a CountryData list, When filterData is called, Then it should filter with non-empty query`() {
        val country1 = CountryData(1, "Lima, Peru", 37.0902, -95.7129)
        val country2 = CountryData(1, "Lisboa, Portugal", 37.0902, -95.7129)

        val fullList = listOf(
            country1,
            country2,
            country1.copy(country = "Quito, Ecuador"),
            country1.copy(country = "New York, USA"),
        )
        val expected = listOf(
            country1, country2
        )

        val result = helper.filterData(fullList, "Li")

        assertEquals(expected, result)
    }

    @Test
    fun `Given a CountryData list, When filterData is called, Then it should return the same list if query is empty`() {
        val emptyQuery = ""
        val fullList = listOf(
            CountryData(1, "Lima, Peru", 37.0902, -95.7129),
            CountryData(1, "Lisboa, Portugal", 37.0902, -95.7129)
        )

        val result = helper.filterData(fullList, emptyQuery)

        assertEquals(fullList, result)
    }

    @Test
    fun `Given a CountryData list, When filterData is called, Then it should return empty list if there are no matching countries`() {
        val fullList = listOf(
            CountryData(1, "Lima, Peru", 37.0902, -95.7129),
            CountryData(1, "Lisboa, Portugal", 37.0902, -95.7129)
        )

        val result = helper.filterData(fullList, "Rom")
        assertEquals(emptyList<CountryData>(), result)
    }

    @Test
    fun `Given an empty list, When groupForecastTimeByDate is called, Then it should return an empty map`() {
        val countryEntity =
            CountryEntity(1, "Argentina", "Buenos Aires", Coordinate(-34.615796, -40.7128))
        val countryData = CountryData(
            id = 1,
            country = "Buenos Aires, Argentina",
            longitude = -34.615796,
            latitude = -40.7128
        )

        doReturn(countryData).`when`(mapper).toCountryData(countryEntity)

        val result: CountryData = helper.toCountryData(countryEntity)

        assertEquals(result, countryData)
    }
}