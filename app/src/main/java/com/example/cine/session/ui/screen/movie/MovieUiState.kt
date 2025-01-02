package com.example.cine.session.ui.screen.movie

import com.example.cine.session.data.model.MovieInfo
import com.example.cine.session.data.remote.response.movie.ListMoviesResponse

data class MovieUiState(
    val movie: MovieInfo = MovieInfo(),
    val upcomingMovies: ListMoviesResponse? = null,
    val similarMovies: List<MovieInfo>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)