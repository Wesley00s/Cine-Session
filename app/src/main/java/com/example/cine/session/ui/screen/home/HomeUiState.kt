package com.example.cine.session.ui.screen.home

import com.example.cine.session.data.remote.response.movie.ListMoviesResponse
import com.example.cine.session.data.remote.response.series.ListSeriesResponse

data class HomeUiState(
    val popularMovies: ListMoviesResponse? = null,
    val topRatedMovies: ListMoviesResponse? = null,
    val favoritesMovies: ListMoviesResponse? = null,
    val popularSeries: ListSeriesResponse? = null,
)