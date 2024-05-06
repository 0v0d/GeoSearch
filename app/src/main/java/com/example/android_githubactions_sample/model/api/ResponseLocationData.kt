package com.example.android_githubactions_sample.model.api

import android.os.Parcelable
import com.example.android_githubactions_sample.model.domain.LocationData
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
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
    val country: String
) : Parcelable

fun ResponseLocationData.toDomainModel() = LocationData(
    name = name,
    localNames = localNames.mapValues { it.value },
    lat = lat.toString(),
    lon = lon.toString(),
    country = country
)