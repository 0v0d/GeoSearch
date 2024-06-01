package com.example.androidgithubactionssample.repository

import com.example.androidgithubactionssample.BuildConfig
import com.example.androidgithubactionssample.data.remote.GeoLocationNetWorkDataSource
import com.example.androidgithubactionssample.model.api.ResponseLocationData
import retrofit2.Response
import javax.inject.Inject

interface GeoLocationRepository {
    suspend fun execute(q: String): Response<List<ResponseLocationData>>?
}

class GeoLocationRepositoryImpl
    @Inject
    constructor(
        private val geoLocationService: GeoLocationNetWorkDataSource,
    ) : GeoLocationRepository {
        private val appId = BuildConfig.API_KEY

        override suspend fun execute(q: String): Response<List<ResponseLocationData>>? {
            return getLocation(q)
        }

        private suspend fun getLocation(q: String): Response<List<ResponseLocationData>>? {
            return try {
                geoLocationService.getLocation(q, appId)
            } catch (e: Exception) {
                null
            }
        }
    }
