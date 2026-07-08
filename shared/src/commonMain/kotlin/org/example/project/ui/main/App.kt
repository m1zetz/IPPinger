package org.example.project.ui.main


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Tablet
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.project.model.Result
import org.example.project.ui.theme.neutral
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
                if (state.isLoading) {
                    Spacer(Modifier.size(4.dp))
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(12.dp),
                        strokeWidth = 2.dp
                    )
                }
            }

            val nameList = listOf<String>("Название", "Все IP предприятия", "Текущий IP", "Результат")
            Cap(nameList)
            HorizontalDivider()

            if (state.error != null) {
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

@Composable
fun Cap(names: List<String>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = neutral,
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(Modifier.padding(horizontal = 12.dp).size(24.dp))

            names.forEachIndexed { index, name ->
                Box(
                    modifier = Modifier
                        .weight(1f),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text(
                        text = name
                    )
                }
            }

        }
    }
}