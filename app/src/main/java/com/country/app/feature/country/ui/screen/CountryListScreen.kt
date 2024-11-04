package com.country.app.feature.country.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.country.app.feature.country.domain.model.CountryData
import com.country.app.feature.country.ui.component.SearchableToolbar
import com.country.app.feature.country.ui.component.CountryList

@Composable
fun CountryListScreen(
    list: List<CountryData>,
    searchQuery: String,
    onQueryUpdated: (String) -> Unit,
    onCountryClick: (CountryData) -> Unit
) {

    val scrollState = rememberLazyListState()

    Column(modifier = Modifier.fillMaxSize()) {
        SearchableToolbar(searchQuery = searchQuery, onQueryUpdated = onQueryUpdated::invoke)
        CountryList(list = list, scrollState = scrollState, onCountryClick = onCountryClick)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCountryListScreen() {
    val countryList = listOf(
        CountryData(id = 1, country = "Lima, Peru", latitude = -12.0464, longitude = -77.0428),
        CountryData(
            id = 2,
            country = "Buenos Aires, Argentina",
            latitude = -12.0464,
            longitude = -77.0428
        )
    )

    CountryListScreen(
        list = countryList,
        onQueryUpdated = {},
        onCountryClick = {},
        searchQuery = ""
    )
}
