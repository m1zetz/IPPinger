package org.example.project.ui.util


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.model.Result
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.max
import org.example.project.ui.theme.failure
import org.example.project.ui.theme.success


@Composable
fun InfoCard(result: Result) {
    val color = if(result is Result.Failure){
        failure
    } else {
        success
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = color,
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            val icon = if(result is Result.Failure) {
                Icons.Default.Close
            } else {
                Icons.Default.Check
            }
            Icon(
                icon,
                contentDescription = "Info",
                modifier = Modifier.padding(horizontal = 12.dp).size(24.dp),
            )

            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = result.enterprise.name,
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.TopCenter
            ) {
                Column {
                    result.enterprise.ip_addresses.forEach { ipAddress ->
                        Text(
                            text = ipAddress.toString(),
                            maxLines = 1,
                        )
                    }
                }

            }
            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = result.ip,
                    maxLines = 1,
                )

            }
            val message = if (result is Result.Failure) {
                result.message
            } else {
                "OK"
            }
            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = message
                )
            }
        }
    }
}