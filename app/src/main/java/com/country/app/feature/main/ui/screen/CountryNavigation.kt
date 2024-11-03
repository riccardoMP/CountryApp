package com.country.app.feature.main.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.country.app.feature.country.viewmodel.CountryViewModel
import com.country.app.feature.country.ui.screen.CountryScreen
import com.country.app.feature.details.ui.CountryDetailsScreen
import com.country.app.util.Screen
import com.country.app.util.Screen.Companion.LATITUDE
import com.country.app.util.Screen.Companion.LONGITUDE

@Composable
fun CountryNavHost(modifier: Modifier = Modifier) {
    val navigation = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navigation,
        startDestination = Screen.CountryScreen.route
    ) {
        composable(route = Screen.CountryScreen.route) {
            val viewModel = hiltViewModel<CountryViewModel>()

            CountryScreen(
                uiState = viewModel.countryUIState.collectAsState(),
                onQueryUpdated = viewModel::updateSearchQuery,
                navHostController = navigation
            )
        }

        composable(Screen.DetailsScreen.route) { backStackEntry ->
            val latitude = backStackEntry.arguments?.getString(LATITUDE)?.toDoubleOrNull() ?: 0.0
            val longitude = backStackEntry.arguments?.getString(LONGITUDE)?.toDoubleOrNull() ?: 0.0

            CountryDetailsScreen(
                navHostController = navigation,
                latitude = latitude,
                longitude = longitude
            )
        }
    }
}
