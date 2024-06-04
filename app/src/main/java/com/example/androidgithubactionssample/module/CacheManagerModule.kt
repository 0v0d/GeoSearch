package com.example.androidgithubactionssample.module

import com.example.androidgithubactionssample.CacheManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheManagerModule {
    /**
     * キャッシュマネージャを提供する
     * @return キャッシュマネージャ
     */
    @Provides
    @Singleton
    fun provideCacheManager(): CacheManager = CacheManager()
}
