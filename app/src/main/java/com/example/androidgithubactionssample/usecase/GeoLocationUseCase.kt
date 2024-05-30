package com.example.androidgithubactionssample.usecase

import com.example.androidgithubactionssample.model.api.ResponseLocationData
import retrofit2.Response

interface GeoLocationUseCase {
    suspend fun execute(q: String): Response<List<ResponseLocationData>>?
}
