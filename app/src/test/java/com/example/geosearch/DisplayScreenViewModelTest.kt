package com.example.geosearch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.geosearch.model.api.ResponseLocationData
import com.example.geosearch.model.api.toDomainModel
import com.example.geosearch.model.domain.LocationData
import com.example.geosearch.ui.screen.display.DisplayScreenViewModel
import com.example.geosearch.usecase.GeoLocationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DisplayScreenViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DisplayScreenViewModel

    @Mock
    private lateinit var useCase: GeoLocationUseCase

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = DisplayScreenViewModel(useCase = useCase)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    private val mockData =
        ResponseLocationData(
            name = "Tokyo",
            localNames =
                mapOf(
                    "cy" to "Tokyo",
                    "es" to "Tokio",
                    "pl" to "Tokio",
                    "en" to "Tokyo",
                    "tr" to "Tokyo",
                    "ja" to "東京都",
                ),
            lat = 35.6828387,
            lon = 139.7594549,
            country = "JP",
        )
    private val response = listOf(mockData)
    private val emptyResponse = emptyList<ResponseLocationData>()

    @Test
    fun searchSuccessResponse() =
        runTest {
            val keyword = "Tokyo"
            `when`(useCase(keyword)).thenReturn(Response.success(response))
            println(response)
            viewModel.searchLocation(keyword)
            assertEquals(mockData.toDomainModel(), viewModel.result.value)
        }

    @Test
    fun searchNullResponse() =
        runTest {
            val keyword = "Tokyo"
            `when`(useCase(keyword)).thenReturn(Response.success(null))

            viewModel.searchLocation(keyword)
            assertEquals(LocationData("", emptyMap(), "0", "0", ""), viewModel.result.value)
        }

    @Test
    fun searchEmptyResponse() =
        runTest {
            val keyword = "Tokyo"
            `when`(useCase(keyword)).thenReturn(Response.success(emptyResponse))

            viewModel.searchLocation(keyword)
            assertEquals(LocationData("", emptyMap(), "0", "0", ""), viewModel.result.value)
        }
}
