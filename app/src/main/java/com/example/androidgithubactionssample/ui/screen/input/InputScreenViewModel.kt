package com.example.androidgithubactionssample.ui.screen.input

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InputScreenViewModel
    @Inject
    constructor() : ViewModel() {
        private val _inputText = mutableStateOf("")
        val inputText: State<String> = _inputText

        fun updateInputText(newText: String) {
            _inputText.value = newText
        }
    }
