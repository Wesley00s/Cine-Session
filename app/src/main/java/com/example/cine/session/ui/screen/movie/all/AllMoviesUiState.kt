package com.example.cine.session.ui.screen.movie.all

import com.example.cine.session.data.remote.response.movie.ListMoviesResponse

data class AllMoviesUiState (
    val topRatedMovies: ListMoviesResponse? = null,
    val popularMovies: ListMoviesResponse? = null,
    val upcomingMovies: ListMoviesResponse? = null
)