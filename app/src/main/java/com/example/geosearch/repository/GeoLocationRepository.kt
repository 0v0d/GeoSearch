package com.example.geosearch.repository

import com.example.geosearch.BuildConfig
import com.example.geosearch.CacheManager
import com.example.geosearch.model.api.ResponseLocationData
import com.example.geosearch.remote.GeoLocationNetWorkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

interface GeoLocationRepository {
    suspend fun execute(keyword: String): Response<List<ResponseLocationData>>?
}

class GeoLocationRepositoryImpl
    @Inject
    constructor(
        private val cacheManager: CacheManager,
        private val geoLocationService: GeoLocationNetWorkDataSource,
    ) : GeoLocationRepository {
        private val appId = BuildConfig.API_KEY

        override suspend fun execute(keyword: String): Response<List<ResponseLocationData>>? {
            return getLocation(keyword)
        }

        private suspend fun getLocation(keyword: String): Response<List<ResponseLocationData>>? =
            withContext(Dispatchers.IO) {
                return@withContext try {
                    cacheManager.get(keyword)?.let {
                        return@withContext it
                    }

                    geoLocationService.getLocation(keyword, appId).also {
                        cacheManager.put(keyword, it)
                    }
                } catch (e: Exception) {
                    null
                }
            }
    }
