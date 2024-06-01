package com.example.androidgithubactionssample.ui.screen.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidgithubactionssample.ui.SearchBar

const val TITLE = "InputScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen(
    onSearch: (String) -> Unit,
    viewModel: InputScreenViewModel = hiltViewModel(),
) {
    val inputText by viewModel.inputText
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(TITLE) },
            )
        },
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SearchBar(
                query = inputText,
                onQueryChange = { newValue ->
                    viewModel.updateInputText(newValue)
                },
                onSearch = {
                    onSearch(inputText)
                },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
