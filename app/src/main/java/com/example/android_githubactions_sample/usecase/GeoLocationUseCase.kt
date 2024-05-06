package com.example.android_githubactions_sample.usecase

import com.example.android_githubactions_sample.model.api.ResponseLocationData
import retrofit2.Response

interface GeoLocationUseCase {
    suspend fun execute(q: String): Response<List<ResponseLocationData>>?
}