package com.example.android_githubactions_sample.repository

import android.util.Log
import com.example.android_githubactions_sample.BuildConfig
import com.example.android_githubactions_sample.DataSource.GeoLocationNetWorkDataSource
import com.example.android_githubactions_sample.usecase.GeoLocationUseCase
import com.example.android_githubactions_sample.model.api.ResponseLocationData
import retrofit2.Response
import javax.inject.Inject

class GeoLocationRepository @Inject constructor(
    private val geoLocationService: GeoLocationNetWorkDataSource
) : GeoLocationUseCase {
    private val appId = BuildConfig.API_KEY

    override suspend fun execute(q: String): Response<List<ResponseLocationData>>? {
        return getLocation(q)
    }

    private suspend fun getLocation(
        q: String,
    ): Response<List<ResponseLocationData>>? {
        return try {
            geoLocationService.getLocation(q, appId)
        } catch (e: Exception) {
            null
        }
    }
}