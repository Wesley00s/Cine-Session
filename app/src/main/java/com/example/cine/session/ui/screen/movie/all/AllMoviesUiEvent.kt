package com.example.cine.session.ui.screen.movie.all

sealed class AllMoviesUiEvent {
    data class LoadTopRatedMovies(val page: Int) : AllMoviesUiEvent()
    data class LoadPopularMovies(val page: Int) : AllMoviesUiEvent()
    data class LoadUpcomingMovies(val page: Int) : AllMoviesUiEvent()
}