package com.example.geosearch.remote

import com.example.geosearch.model.api.ResponseLocationData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoLocationNetWorkDataSource {
    @GET("/geo/1.0/direct")
    suspend fun getLocation(
        @Query("q") q: String,
        @Query("appid") appId: String,
    ): Response<List<ResponseLocationData>>
}
