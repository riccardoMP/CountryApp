package com.country.app.feature.country.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.country.app.feature.country.viewmodel.CountryViewModel

@Composable
fun CountryApp(modifier: Modifier = Modifier) {
    val navigation = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navigation,
        startDestination = "countryList"
    ) {
        composable(
            route = "countryList"
        ) {
            val viewModel = hiltViewModel<CountryViewModel>()

            CountryScreen(viewModel = viewModel)
        }
    }
}
