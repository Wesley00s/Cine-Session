package com.example.cine.session.ui.screen.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import com.example.cine.session.core.network.util.isScreenLandscape
import com.example.cine.session.data.model.MovieInfo
import com.example.cine.session.data.model.SerieInfo
import com.example.cine.session.ui.component.AnimatedCarousel
import com.example.cine.session.ui.component.HorizontalList
import com.example.cine.session.ui.theme.Primary

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit,
    onNavigateToMovie: (MovieInfo) -> Unit,
    onNavigateToSerie: (SerieInfo) -> Unit
) {
    LaunchedEffect(true) {
        onEvent(HomeUiEvent.LoadPupularMovieList(1))
        onEvent(HomeUiEvent.LoadTopRatedMovieList(1))
        onEvent(HomeUiEvent.LoadFavoritesMovieList(1, "7db984741d1d3e7d21ef85e902cc935cc6a71887"))
        onEvent(HomeUiEvent.LoadPopularSeriesList(1))
    }

    val isLandscape = isScreenLandscape()
    val scrollState = rememberLazyListState()

    LazyColumn(
        modifier = modifier
            .background(Primary),
        state = scrollState,
    ) {
        item {

            var popularMovieInfo by remember { mutableStateOf<List<MovieInfo>?>(null) }
            var topRatedMovieInfo by remember { mutableStateOf<List<MovieInfo>?>(null) }
            var popularSeriesInfo by remember { mutableStateOf<List<SerieInfo>?>(null) }

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

            Log.d("PopularMovies", "Response:" + popularMovieInfo.toString())
            if (!popularMovieInfo.isNullOrEmpty())
                AnimatedCarousel(
                    modifier = modifier,
                    movies = popularMovieInfo.orEmpty(),
                    onMovieClick = {
                        onNavigateToMovie(it)
                    }
                )


            Log.d("TopRatedMovies", "Response:" + topRatedMovieInfo.toString())
            if (!topRatedMovieInfo.isNullOrEmpty())
                HorizontalList(
                    items = topRatedMovieInfo.orEmpty(),
                    text = "Popular Movies",
                    onItemRender = { movie ->
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/original${movie.posterPath}",
                            contentDescription = "Poster",
                            modifier = Modifier.fillMaxSize()
                        )
                    },
                    onItemClick = {
                        onNavigateToMovie(it)
                    }
                )


            Log.d("PopularSeries", "Response:" + popularSeriesInfo.toString())
            if (!popularSeriesInfo.isNullOrEmpty())
                HorizontalList(
                    modifier = modifier,
                    text = "Popular Series",
                    items = popularSeriesInfo.orEmpty(),
                    onItemRender = { serie ->
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/original${serie.posterPath}",
                            contentDescription = "Poster",
                            modifier = Modifier.fillMaxSize()
                        )
                    },
                    onItemClick = {
                        onNavigateToSerie(it)
                    }
                )


        }
    }
}