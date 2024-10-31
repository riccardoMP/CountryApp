package com.country.app.feature.country.ui

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ClearAll
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import com.country.app.feature.country.ui.component.CountryList
import com.country.app.feature.country.viewmodel.CountryViewModel

@Composable
fun CountryListScreen(
    viewModel: CountryViewModel,
) {

    val filteredCountries by viewModel.countryList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }


    val scrollState = rememberLazyListState()
    val activity = LocalContext.current as ComponentActivity

    BackHandler {
        activity.finish()
    }

    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.fillMaxWidth())

        SearchableToolbar {
            viewModel.filterData(query = it )
        }

        if (filteredCountries.isEmpty()) {
            EmptyScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
            )
        } else {
            CountryList(list = filteredCountries, scrollState)
        }
    }
}
