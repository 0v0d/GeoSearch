package com.example.geosearch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.geosearch.ui.screen.input.InputScreenViewModel
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class InputScreenViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: InputScreenViewModel

    @Before
    fun setup() {
        viewModel = InputScreenViewModel()
    }

    @Test
    fun initialInputTextState() {
        assertEquals("", viewModel.inputText.value)
    }

    @Test
    fun updateInputTextState() {
        val newText = "New Text"

        viewModel.updateInputText(newText)

        assertEquals(newText, viewModel.inputText.value)
    }
}
