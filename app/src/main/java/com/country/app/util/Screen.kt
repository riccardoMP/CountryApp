package com.country.app.util


sealed class Screen(val route: String) {

    companion object {
        const val LATITUDE: String = "LATITUDE"
        const val LONGITUDE: String = "LONGITUDE"
    }

    data object CountryScreen : Screen("CountryScreen")

    data object DetailsScreen : Screen("DetailsScreen/{$LATITUDE}/{$LONGITUDE}") {
        fun passId(latitude: Double, longitude: Double): String {
            return this.route
                .replace(oldValue = "{$LATITUDE}", newValue = latitude.toString())
                .replace(oldValue = "{$LONGITUDE}", newValue = longitude.toString())
        }
    }
}