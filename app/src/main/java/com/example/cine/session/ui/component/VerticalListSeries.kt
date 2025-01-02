package com.example.cine.session.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cine.session.data.model.SerieInfo
import com.example.cine.session.ui.theme.AppTypography

@Composable
fun VerticalListSeries(
    modifier: Modifier = Modifier,
    items: List<SerieInfo>,
    onNavigateToSerie: (SerieInfo) -> Unit = {}
) {

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            if (items.isNotEmpty()) {
                items.chunked(2).forEach { movie ->
                    Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        movie.forEach { serieInfo ->
                            SerieCard(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(bottom = 16.dp),
                                serieInfo = serieInfo,
                                onClick = onNavigateToSerie
                            )
                        }
                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "No series found",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Gray,
                        style = AppTypography.headlineMedium
                    )
                }
            }
        }
    }
}