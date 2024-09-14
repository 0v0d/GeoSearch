package com.example.geosearch.model.api

import com.example.geosearch.model.domain.LocationData
import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLocationData(
    @Json(name = "name")
    val name: String,
    @Json(name = "local_names")
    val localNames: Map<String, String>,
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "lon")
    val lon: Double,
    @Json(name = "country")
    val country: String,
)

fun ResponseLocationData.toDomainModel() =
    LocationData(
        name = name,
        localNames = localNames.mapValues { it.value },
        lat = lat.toString(),
        lon = lon.toString(),
        country = country,
    )
