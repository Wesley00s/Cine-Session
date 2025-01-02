package com.example.cine.session.ui.screen.tv_show

import androidx.compose.foundation.background
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cine.session.core.network.util.isScreenLandscape
import com.example.cine.session.data.model.SerieInfo
import com.example.cine.session.ui.component.AnimatedCarousel
import com.example.cine.session.ui.component.MovieHorizontalList
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
    onViewAllSeries: (List<SerieInfo>) -> Unit
) {

    LaunchedEffect(true) {
        onEvent(TVShowUiEvent.LoadPopularSeries(1))
        onEvent(TVShowUiEvent.LoadTopRatedSeries(1))
//        onEvent(TVShowUiEvent.LoadUpcomingSeries(1))
    }

    val scrollState = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        state = scrollState,
    ) {
        item {

            var popularSeriesInfo by remember { mutableStateOf<List<SerieInfo>?>(null) }
            var topRatedSeriesInfo by remember { mutableStateOf<List<SerieInfo>?>(null) }
//            var upcomingSeriesInfo by remember { mutableStateOf<List<SerieInfo>?>(null) }

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

//            upcomingSeriesInfo = uiState.upcomingSeries?.let {
//                it.results.let { results ->
//                    results.map { result ->
//                        SerieInfo(
//                            id = result.id,
//                            posterPath = result.posterPath,
//                            name = result.name,
//                            overview = result.overview,
//                            voteAverage = result.voteAverage,
//                            voteCount = result.voteCount,
//                            backdropPath = result.backdropPath,
//                            originalName = result.originalName,
//                            popularity = result.popularity,
//                        )
//                    }
//                }
//            }

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
                    AnimatedCarousel(
                        modifier = Modifier,
                        items = popularSeriesInfo.orEmpty(),
                        onItemClick = onNavigateToSerie

                    ) { movie, animatedScale ->
                        ImageFormat(
                            path = "https://image.tmdb.org/t/p/original${movie.backdropPath}",
                            isLandscape = isScreenLandscape(),
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer(
                                    scaleX = animatedScale.value,
                                    scaleY = animatedScale.value
                                )
                                .fillMaxWidth()
                        )
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
                            movie.name?.let {
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
                        modifier = modifier,
                        text = "Top Rated Series",
                        series = topRatedSeriesInfo.orEmpty(),
                        onItemClick = onNavigateToSerie,
                        onViewAllSeries = onViewAllSeries
                    )

//                if (!upcomingSeriesInfo.isNullOrEmpty())
//                    HorizontalList(
//                        modifier = modifier,
//                        text = "Upcoming Series",
//                        items = upcomingSeriesInfo.orEmpty(),
//                        onItemRender = { serie ->
//                            AsyncImage(
//                                model = "https://image.tmdb.org/t/p/w400${serie.posterPath}",
//                                contentDescription = "Poster",
//                                modifier = Modifier.fillMaxSize()
//                            )
//                        },
//                        onItemClick = onNavigateToSerie,
//                        onViewAll = onViewAllSeries
//                    )
            }
        }
    }

}