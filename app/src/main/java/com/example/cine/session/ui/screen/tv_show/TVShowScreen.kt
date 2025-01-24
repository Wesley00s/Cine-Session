package com.example.cine.session.ui.screen.tv_show

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cine.session.core.network.util.isScreenLandscape
import com.example.cine.session.data.model.SerieInfo
import com.example.cine.session.data.remote.response.series.ListSeriesResponse
import com.example.cine.session.ui.component.Carousel
import com.example.cine.session.ui.component.ImageFormat
import com.example.cine.session.ui.component.SerieHorizontalList
import com.example.cine.session.ui.theme.AppTypography
import com.example.cine.session.ui.theme.Primary
import com.example.cine.session.ui.theme.Quaternary

@Composable
fun TVShowScreen(
    modifier: Modifier = Modifier,
    uiState: TVShowUiState,
    onEvent: (TVShowUiEvent) -> Unit,
    onNavigateToSerie: (SerieInfo) -> Unit,
    onViewAllSeries: (ListSeriesResponse) -> Unit
) {

    LaunchedEffect(true) {
        onEvent(TVShowUiEvent.LoadPopularSeries(1))
        onEvent(TVShowUiEvent.LoadTopRatedSeries(1))
        onEvent(TVShowUiEvent.LoadAiringTodaySeries(1))
    }

    val scrollState = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        state = scrollState,
    ) {
        item {

            var popularSeriesInfo by remember { mutableStateOf<List<SerieInfo>?>(null) }
            var topRatedSeriesInfo by remember { mutableStateOf<List<SerieInfo>?>(null) }
            var upcomingSeriesInfo by remember { mutableStateOf<List<SerieInfo>?>(null) }

            popularSeriesInfo = uiState.popularSeries?.let {
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

            topRatedSeriesInfo = uiState.topRatedSeries?.let {
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

            upcomingSeriesInfo = uiState.upcomingSeries?.let {
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

            if (popularSeriesInfo.isNullOrEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Primary)
                ) {

                    CircularProgressIndicator(
                        modifier = Modifier
                            .offset(y = 400.dp)
                            .size(50.dp)
                            .align(Alignment.BottomCenter),
                        color = Quaternary
                    )
                }
            } else {

                if (!popularSeriesInfo.isNullOrEmpty())
                    Carousel(
                        modifier = Modifier,
                        items = popularSeriesInfo.orEmpty(),
//                        onItemClick = onNavigateToSerie

                    ) { serie ->
                        Box(modifier = Modifier) {
                            ImageFormat(
                                path = "https://image.tmdb.org/t/p/original${serie.backdropPath}",
                                isLandscape = isScreenLandscape(),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .fillMaxWidth()
                            )

                            Icon(
                                modifier = Modifier
                                    .size(50.dp)
                                    .background(
                                        Color.Black.copy(alpha = 0.7f),
                                        shape = RoundedCornerShape(100.dp)
                                    )
                                    .border(2.dp, Quaternary, RoundedCornerShape(100.dp))
                                    .align(Alignment.Center)
                                    .shadow(elevation = 500.dp, spotColor = Color.Black)
                                    .clickable { onNavigateToSerie(serie) },
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Play",
                                tint = Quaternary,
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                                .padding(16.dp)
                                .offset(y = -(50).dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "POPULAR",
                                style = AppTypography.headlineSmall.copy(
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Gray
                                )
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            serie.name?.let {
                                Text(
                                    text = it,
                                    style = AppTypography.headlineMedium.copy(
                                        fontWeight = FontWeight.Black
                                    )
                                )
                            }
                        }
                    }

                if (!topRatedSeriesInfo.isNullOrEmpty())
                    SerieHorizontalList(
                        modifier = Modifier,
                        text = "Top Rated Series",
                        series = topRatedSeriesInfo.orEmpty(),
                        onItemClick = onNavigateToSerie,
                        onViewAllSeries = { onViewAllSeries(it) }
                    )

                if (!upcomingSeriesInfo.isNullOrEmpty())
                    SerieHorizontalList(
                        modifier = modifier,
                        text = "Airing Today",
                        series = upcomingSeriesInfo.orEmpty(),
                        onItemClick = onNavigateToSerie,
                        onViewAllSeries = { onViewAllSeries(it) }
                    )
            }
        }
    }

}