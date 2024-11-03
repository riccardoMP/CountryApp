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
import com.country.app.util.Screen.Companion.DETAILS_ID

object Route {

    const val StartDestination = "countryList"
    const val TopHeadlineScreenNewsSources = "top-headline/newsId/{newsId}"
    const val TopHeadlineScreenNewsByCountry = "top-headline/country/{country}"
    const val TopHeadlineScreenNewsByLanguage = "top-headline/language/{language}"
    const val NetworkTopHeadlines = "top-headline-network"
    const val OfflineTopHeadlines = "top-headline-offline"
    const val PagingTopHeadlines = "top-headline-paging"

    fun topHeadlineScreenWithCountry(country: String): String {
        return "top-headline/country/$country"
    }

    fun topHeadlineScreenWithId(newsId: String): String {
        return "top-headline/newsId/$newsId"
    }

    fun topHeadlineScreenWithLanguage(language: String): String {
        return "top-headline/language/$language"
    }
}

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
            val countryId = backStackEntry.arguments?.getString(DETAILS_ID).orEmpty()
            CountryDetailsScreen(navHostController = navigation, countryId = countryId)
        }
    }
}
