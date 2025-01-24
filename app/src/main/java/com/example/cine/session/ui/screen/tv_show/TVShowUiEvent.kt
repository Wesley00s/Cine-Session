package com.example.cine.session.ui.screen.tv_show

sealed class TVShowUiEvent {
    data class LoadPopularSeries(val page: Int): TVShowUiEvent()
    data class LoadTopRatedSeries(val page: Int): TVShowUiEvent()
    data class LoadAiringTodaySeries(val page: Int): TVShowUiEvent()
}