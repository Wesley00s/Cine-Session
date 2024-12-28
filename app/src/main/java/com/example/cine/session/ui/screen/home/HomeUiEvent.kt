package com.example.cine.session.ui.screen.home

sealed class HomeUiEvent {
    data class LoadPupularMovieList(val page: Int) : HomeUiEvent()
    data class LoadTopRatedMovieList(val page: Int) : HomeUiEvent()
    data class LoadFavoritesMovieList(val page: Int, val sessionId: String) : HomeUiEvent()
    data class LoadPopularSeriesList(val page: Int) : HomeUiEvent()
}