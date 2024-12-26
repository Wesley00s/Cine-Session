package com.example.cine.session.ui.screen.home

import com.example.cine.session.data.model.InfoCard
import com.example.cine.session.data.model.MovieInfo
import com.example.cine.session.data.remote.response.movie.ListMoviesResponse

data class HomeUiState(
    val movies: ListMoviesResponse? = null
)