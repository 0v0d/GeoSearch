package com.example.geosearch.model.domain

import kotlinx.serialization.Serializable
import java.util.Locale

@Serializable
data class LocationData(
    val name: String,
    val localNames: Map<String, String>,
    val lat: String,
    val lon: String,
    val country: String,
) {
    fun getLocalName(): String {
        return localNames[Locale.getDefault().language] ?: ""
    }
}
