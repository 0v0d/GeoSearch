package com.example.geosearch.usecase

import com.example.geosearch.repository.GeoLocationRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeoLocationUseCase
    @Inject
    constructor(private val geoLocationRepository: GeoLocationRepository) {
        suspend operator fun invoke(keyword: String) = geoLocationRepository.execute(keyword)
    }
