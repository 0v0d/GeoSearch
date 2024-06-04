package com.example.androidgithubactionssample.usecase

import com.example.androidgithubactionssample.repository.GeoLocationRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeoLocationUseCase
    @Inject
    constructor(private val geoLocationRepository: GeoLocationRepository) {
        suspend operator fun invoke(keyword: String) = geoLocationRepository.execute(keyword)
    }
