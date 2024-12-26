package com.example.cine.session.ui.screen.movie

sealed class MovieUiEvent {
    data class LoadMovie(val movieId: Int) : MovieUiEvent()
    data class LoadSimilarMovies(val movieId: Int, val page: Int) : MovieUiEvent()
}