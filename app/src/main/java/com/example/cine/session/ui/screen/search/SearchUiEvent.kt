package com.example.cine.session.ui.screen.search

sealed class SearchUiEvent {
    data class SearchMovies(val query: String, val page: Int) : SearchUiEvent()
    data class SearchSeries(val query: String, val page: Int) : SearchUiEvent()
}