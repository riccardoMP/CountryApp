package com.country.app.feature.country.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.country.app.feature.country.domain.model.CountryData
import com.country.app.feature.country.ui.component.CountryList

@Composable
fun CountryListScreen(list: List<CountryData>, onQueryUpdated: (String) -> Unit) {

    val scrollState = rememberLazyListState()

    Column(modifier = Modifier.fillMaxSize()) {
        SearchableToolbar(onQueryUpdated = onQueryUpdated::invoke)
        CountryList(list = list, scrollState)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCountryListScreen() {
    val countryList = listOf(
        CountryData(country = "Lima, Peru", latitude = -12.0464, longitude = -77.0428),
        CountryData(country = "Buenos Aires, Argentina", latitude = -12.0464, longitude = -77.0428)
    )

    CountryListScreen(
        list = countryList,
        onQueryUpdated = {}
    )
}
