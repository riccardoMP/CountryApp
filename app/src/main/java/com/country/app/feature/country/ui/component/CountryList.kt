package com.country.app.feature.country.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.country.app.feature.country.domain.model.CountryData

@Composable
fun CountryList(list: List<CountryData>, scrollState: LazyListState) = LazyColumn(
    modifier = Modifier
        .fillMaxSize()
        .navigationBarsPadding(),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
    state = scrollState,
) {
    items(items = list) { data ->
        CountryItem(data = data)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCountryList() {
    val countryList = listOf(
        CountryData(country = "Lima, Peru", latitude = -12.0464, longitude = -77.0428),
        CountryData(country = "Buenos Aires, Argentina", latitude = -12.0464, longitude = -77.0428)
    )
    val scrollState = remember { LazyListState() }

    CountryList(list = countryList, scrollState = scrollState)

}