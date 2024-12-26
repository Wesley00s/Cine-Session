package com.example.cine.session.ui.screen.home

sealed class HomeUiEvent {
    data class LoadPupularMovieList(val page: Int) : HomeUiEvent()
}