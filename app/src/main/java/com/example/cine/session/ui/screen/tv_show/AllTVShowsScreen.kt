package com.example.cine.session.ui.screen.tv_show

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cine.session.data.model.SerieInfo
import com.example.cine.session.data.remote.response.series.ListSeriesResponse
import com.example.cine.session.ui.component.VerticalListSeries
import com.example.cine.session.ui.theme.AppTypography

@Composable
fun AllTVShowsScreen(
    modifier: Modifier = Modifier,
    listSeriesResponse: ListSeriesResponse,
    onNavigateToSerie: (SerieInfo) -> Unit,
    onBackClick: () -> Unit
) {
    var serieInfo by remember { mutableStateOf<List<SerieInfo>?>(null) }

    serieInfo = listSeriesResponse.let {
        it.results.let { results ->
            results.map { result ->
                SerieInfo(
                    id = result.id,
                    posterPath = result.posterPath,
                    name = result.name,
                    overview = result.overview,
                    voteAverage = result.voteAverage,
                    voteCount = result.voteCount,
                    backdropPath = result.backdropPath,
                    originalName = result.originalName,
                    popularity = result.popularity,
                )
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(.8f),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                    Text(text = "All Series", style = AppTypography.headlineMedium)
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (!serieInfo.isNullOrEmpty()) {
                VerticalListSeries(
                    items = serieInfo.orEmpty(),
                    onNavigateToSerie = onNavigateToSerie,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
