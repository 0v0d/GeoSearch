package com.example.androidgithubactionssample

import android.util.LruCache
import com.example.androidgithubactionssample.model.api.ResponseLocationData
import retrofit2.Response

class CacheManager(cacheSize: Int = 5) {
    private val cache = LruCache<String, Response<List<ResponseLocationData>>?>(cacheSize)

    fun get(keyword: String): Response<List<ResponseLocationData>>? {
        return cache[keyword]
    }

    fun put(
        keyword: String,
        response: Response<List<ResponseLocationData>>?,
    ) {
        cache.put(keyword, response)
    }
}
