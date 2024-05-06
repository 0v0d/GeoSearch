package com.example.android_githubactions_sample.module

import android.content.Context
import com.example.android_githubactions_sample.DataSource.GeoLocationNetWorkDataSource
import com.example.android_githubactions_sample.repository.GeoLocationRepository
import com.example.android_githubactions_sample.usecase.GeoLocationUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()).build()

    @Provides
    fun provideRetrofit(
        @ApplicationContext context: Context,
        moshi: Moshi
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    fun provideGeoLocationNetWorkDataSource(retrofit: Retrofit): GeoLocationNetWorkDataSource =
        retrofit.create(GeoLocationNetWorkDataSource::class.java)

    @Provides
    fun provideGeoLocationUseCase(repository: GeoLocationRepository): GeoLocationUseCase =
        repository
}