package com.country.core.local.model

data class CountryEntity(
    val id: Int,
    val country: String,
    val city: String,
    val coordinate: Coordinate
) {
    data class Coordinate(
        val longitude: Double,
        val latitude: Double
    )
}