package com.country.app.util


sealed class Screen(val route: String) {

    companion object {
        const val DETAILS_ID: String = "DETAILS_ID"
    }

    data object CountryScreen : Screen("CountryScreen")

    data object DetailsScreen : Screen("DetailsScreen/{$DETAILS_ID}") {
        fun passId(id: Int): String {
            return this.route.replace(oldValue = "{$DETAILS_ID}", newValue = id.toString())
        }
    }
}