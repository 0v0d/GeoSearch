package com.example.geosearch.module

import com.example.geosearch.remote.GeoLocationNetWorkDataSource
import com.example.geosearch.repository.GeoLocationRepository
import com.example.geosearch.repository.GeoLocationRepositoryImpl
import com.example.geosearch.usecase.GeoLocationUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory()).build()

    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    fun provideGeoLocationNetWorkDataSource(retrofit: Retrofit): GeoLocationNetWorkDataSource =
        retrofit.create(GeoLocationNetWorkDataSource::class.java)

    @Provides
    fun provideGeoLocationRepository(repository: GeoLocationRepositoryImpl): GeoLocationRepository = repository

    @Provides
    fun provideGeoLocationUseCase(geoLocationRepository: GeoLocationRepository): GeoLocationUseCase =
        GeoLocationUseCase(geoLocationRepository)
}
