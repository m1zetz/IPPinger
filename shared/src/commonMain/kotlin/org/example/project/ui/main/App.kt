package org.example.project.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.project.model.Result
import org.example.project.ui.util.InfoCard
import org.koin.compose.viewmodel.koinViewModel


@Composable
@Preview
fun App(
    viewModel: AppViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    MaterialTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = {
                println("click")
                viewModel.launch()

            }) {
                Text("Ping")

            }
            if (state.isLoading) {
                CircularProgressIndicator()
            } else if (state.error != null) {
                Text(state.error.toString())
            }

            LazyColumn {
                items(state.results) { result ->
                    InfoCard(result)
                }
            }

        }
    }
}