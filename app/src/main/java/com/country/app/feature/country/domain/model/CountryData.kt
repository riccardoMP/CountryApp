package com.country.app.feature.country.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CountryData(
    val id: Int,
    val country: String,
    val latitude: Double,
    val longitude: Double
)