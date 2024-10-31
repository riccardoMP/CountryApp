package com.country.core.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CountryDTO(
    @SerialName("_id")
    val id: Int?,

    @SerialName("country")
    val country: String?,

    @SerialName("name")
    val name: String?,

    @SerialName("coord")
    val coordinate: CoordinateDTO?
) {

    @Serializable
    internal data class CoordinateDTO(
        @SerialName("lon")
        val longitude: Double?,

        @SerialName("lat")
        val latitude: Double?
    )

}