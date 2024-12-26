package com.example.cine.session.ui.screen.movie

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cine.session.data.model.InfoCard
import com.example.cine.session.data.model.MovieInfo

data class MovieUiState(
    val movie: MovieInfo = MovieInfo(),
    val similarMovies: List<MovieInfo>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)