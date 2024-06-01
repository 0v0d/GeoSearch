package com.example.androidgithubactionssample.ui.screen.display

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidgithubactionssample.R
import com.example.androidgithubactionssample.model.domain.LocationData
import com.example.androidgithubactionssample.theme.Purple80

@Composable
fun DisplayScreen(
    inputText: String,
    onClick: () -> Unit,
    viewModel: DisplayScreenViewModel = hiltViewModel(),
) {
    val location by viewModel.result.collectAsState()
    val isLoading by viewModel.loading.collectAsState()

    LaunchedEffect(inputText) {
        viewModel.searchLocation(inputText)
    }

    Scaffold(
        topBar = { DisplayScreenTopAppBar(onClick = onClick) },
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                LocationInfo(location = location)
            }
        }
    }
}

@Composable
fun LocationInfo(location: LocationData) {
    Column {
        if (location.name.isNotEmpty()) {
            Text(text = location.name)
            Row {
                Text(text = stringResource(R.string.longitude) + location.lon)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(R.string.longitude) + location.lat)
            }
        } else {
            Text(text = "No location found")
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DisplayScreenTopAppBar(onClick: () -> Unit) {
    TopAppBar(
        title = { Text("Display Screen") },
        navigationIcon = {
            IconButton(onClick = { onClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                )
            }
        },
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = Purple80,
            ),
    )
}
