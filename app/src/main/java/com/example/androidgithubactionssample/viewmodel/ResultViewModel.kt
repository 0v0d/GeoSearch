package com.example.androidgithubactionssample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidgithubactionssample.usecase.GeoLocationUseCase
import com.example.androidgithubactionssample.model.domain.LocationData
import com.example.androidgithubactionssample.model.api.ResponseLocationData
import com.example.androidgithubactionssample.model.api.toDomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val useCase: GeoLocationUseCase
) : ViewModel() {
    private val _result =
        MutableStateFlow(LocationData("", emptyMap(), "0", "0", ""))
    val result = _result.asStateFlow()


    fun searchLocation(q: String) {
        viewModelScope.launch {
            val locationResponse = getLocation(q)
            handleLocationResponse(locationResponse)
        }
    }

    private fun handleLocationResponse(response: Response<List<ResponseLocationData>>?) {
        try {
            if (response?.isSuccessful == true) {
                val locationData = response.body()?.firstOrNull()?.toDomainModel()
                _result.value = locationData ?: LocationData("", emptyMap(), "0", "0", "")
            }
        } catch (e: Exception) {
            _result.value = LocationData("", emptyMap(), "0", "0", "")
        }
    }

    private suspend fun getLocation(q: String): Response<List<ResponseLocationData>>? {
        return try {
            useCase.execute(q)
        } catch (e: Exception) {
            null
        }
    }
}