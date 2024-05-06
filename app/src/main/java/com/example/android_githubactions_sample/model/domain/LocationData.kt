package com.example.android_githubactions_sample.model.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Locale

@Parcelize
data class LocationData(
    val name: String,
    val localNames: Map<String, String>,
    val lat: String,
    val lon: String,
    val country: String
) : Parcelable {
    fun getLocalName(): String {
        return localNames[Locale.getDefault().language] ?: ""
    }
}

