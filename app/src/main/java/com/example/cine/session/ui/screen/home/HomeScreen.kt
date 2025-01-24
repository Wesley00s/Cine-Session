package com.example.cine.session.ui.screen.home

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
import com.example.cine.session.data.model.MovieInfo
import com.example.cine.session.data.remote.response.movie.ListMoviesResponse
import com.example.cine.session.ui.component.Carousel
import com.example.cine.session.ui.component.ImageFormat
import com.example.cine.session.ui.component.MovieHorizontalList
import com.example.cine.session.ui.theme.AppTypography
import com.example.cine.session.ui.theme.Primary
import com.example.cine.session.ui.theme.Quaternary

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    page: Int = 1,
    onEvent: (HomeUiEvent) -> Unit,
    onNavigateToMovie: (MovieInfo) -> Unit,
    onViewAllMovies: (ListMoviesResponse) -> Unit,
) {

    LaunchedEffect(true) {
        onEvent(HomeUiEvent.LoadPopularMovieList(page))
        onEvent(HomeUiEvent.LoadTopRatedMovieList(page))
        onEvent(HomeUiEvent.LoadUpcomingMovieList(page))
        onEvent(
            HomeUiEvent.LoadFavoritesMovieList(
                page,
                "7db984741d1d3e7d21ef85e902cc935cc6a71887"
            )
        )
        onEvent(HomeUiEvent.LoadUpcomingMovieList(page))

    }

    val scrollState = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        state = scrollState,
    ) {
        item {

            var popularMovieInfo by remember { mutableStateOf<List<MovieInfo>?>(null) }
            var topRatedMovieInfo by remember { mutableStateOf<List<MovieInfo>?>(null) }
            var upcomingMovieInfo by remember { mutableStateOf<List<MovieInfo>?>(null) }

            popularMovieInfo = uiState.popularMovies?.let {
                it.results.let { results ->
                    results.map { result ->
                        MovieInfo(
                            id = result.id,
                            posterPath = result.posterPath,
                            title = result.title,
                            overview = result.overview,
                            releaseDate = result.releaseDate,
                            voteAverage = result.voteAverage,
                            voteCount = result.voteCount,
                            backdropPath = result.backdropPath,
                            originalTitle = result.originalTitle,
                            popularity = result.popularity
                        )
                    }
                }
            }

            topRatedMovieInfo = uiState.topRatedMovies?.let {
                it.results.let { results ->
                    results.map { result ->
                        MovieInfo(
                            id = result.id,
                            posterPath = result.posterPath,
                            title = result.title,
                            overview = result.overview,
                            releaseDate = result.releaseDate,
                            voteAverage = result.voteAverage,
                            voteCount = result.voteCount,
                            backdropPath = result.backdropPath,
                            originalTitle = result.originalTitle,
                            popularity = result.popularity
                        )
                    }
                }
            }

            upcomingMovieInfo = uiState.upcomingMovies?.let {
                it.results.let { results ->
                    results.map { result ->
                        MovieInfo(
                            id = result.id,
                            posterPath = result.posterPath,
                            title = result.title,
                            overview = result.overview,
                            releaseDate = result.releaseDate,
                            voteAverage = result.voteAverage,
                            voteCount = result.voteCount,
                            backdropPath = result.backdropPath,
                            originalTitle = result.originalTitle,
                            popularity = result.popularity
                        )
                    }
                }
            }

            if (popularMovieInfo.isNullOrEmpty() || topRatedMovieInfo.isNullOrEmpty()) {
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

                if (!popularMovieInfo.isNullOrEmpty())
                    Carousel(
                        modifier = Modifier,
                        items = popularMovieInfo.orEmpty()
                    ) { movie ->
                        Box(modifier = Modifier) {
                            ImageFormat(
                                path = "https://image.tmdb.org/t/p/original${movie.backdropPath}",
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
                                    .clickable { onNavigateToMovie(movie) },
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
                            movie.title?.let {
                                Text(
                                    text = it,
                                    style = AppTypography.headlineMedium.copy(
                                        fontWeight = FontWeight.Black
                                    )
                                )
                            }
                        }
                    }

                if (!topRatedMovieInfo.isNullOrEmpty())
                    MovieHorizontalList(
                        movies = topRatedMovieInfo.orEmpty(),
                        text = "Top Rated Movies",
                        onItemClick = onNavigateToMovie,
                        onViewAllMovies = onViewAllMovies
                    )

                if (!upcomingMovieInfo.isNullOrEmpty())
                    MovieHorizontalList(
                        modifier = modifier,
                        text = "Upcoming Movies",
                        movies = upcomingMovieInfo.orEmpty(),
                        onItemClick = onNavigateToMovie,
                        onViewAllMovies = onViewAllMovies
                    )
            }
        }
    }
}