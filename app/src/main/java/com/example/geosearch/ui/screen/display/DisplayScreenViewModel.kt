package com.example.geosearch.ui.screen.display

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geosearch.model.api.ResponseLocationData
import com.example.geosearch.model.api.toDomainModel
import com.example.geosearch.model.domain.LocationData
import com.example.geosearch.usecase.GeoLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DisplayScreenViewModel
    @Inject
    constructor(
        private val useCase: GeoLocationUseCase,
    ) : ViewModel() {
        private val _result = MutableStateFlow(LocationData("", emptyMap(), "0", "0", ""))
        val result = _result.asStateFlow()

        private val _isLoading = MutableStateFlow(false)
        val isLoading = _isLoading.asStateFlow()

        fun searchLocation(keyword: String) {
            viewModelScope.launch {
                _isLoading.value = true
                try {
                    handleLocationResponse(useCase(keyword))
                } catch (e: Exception) {
                    _result.value = LocationData("", emptyMap(), "0", "0", "")
                    _isLoading.value = false
                }
            }
        }

        private fun handleLocationResponse(response: Response<List<ResponseLocationData>>?) {
            if (response?.isSuccessful == true) {
                val locationData = response.body()?.firstOrNull()?.toDomainModel()
                _result.value = locationData ?: LocationData("", emptyMap(), "0", "0", "")
            }
            _isLoading.value = false
        }
    }
