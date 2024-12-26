package com.example.cine.session.ui.screen.home

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.cine.session.core.network.util.isScreenLandscape
import com.example.cine.session.data.model.MovieInfo
import com.example.cine.session.ui.component.HorizontalList

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit,
    onNavigateToMovie: (MovieInfo) -> Unit
) {
    LaunchedEffect(true) {
        onEvent(HomeUiEvent.LoadPupularMovieList(1))
    }

    val isLandscape = isScreenLandscape()
    val scrollState = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        state = scrollState,
    ) {
        item {

            var infos by remember { mutableStateOf<List<MovieInfo>?>(null) }

            infos = uiState.movies?.let {
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

            Log.d("PopularMovies", "Response:" + infos.toString())

            HorizontalList(
                infos = infos.orEmpty(),
                text = "Popular Movies",
                onMovieClick = {
                    onNavigateToMovie(it)
                }
            )
        }
    }
}