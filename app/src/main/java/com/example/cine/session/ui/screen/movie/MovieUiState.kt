package com.example.cine.session.ui.screen.movie

import com.example.cine.session.data.model.MovieInfo

data class MovieUiState(
    val movie: MovieInfo = MovieInfo(),
    val similarMovies: List<MovieInfo>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)