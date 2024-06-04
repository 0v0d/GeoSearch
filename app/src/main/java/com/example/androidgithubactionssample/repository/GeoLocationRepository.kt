package com.example.androidgithubactionssample.repository

import com.example.androidgithubactionssample.BuildConfig
import com.example.androidgithubactionssample.CacheManager
import com.example.androidgithubactionssample.data.remote.GeoLocationNetWorkDataSource
import com.example.androidgithubactionssample.model.api.ResponseLocationData
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
