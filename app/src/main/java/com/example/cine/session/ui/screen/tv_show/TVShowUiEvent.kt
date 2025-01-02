package com.example.cine.session.ui.screen.tv_show

import com.example.cine.session.data.model.SerieInfo
import com.example.cine.session.ui.screen.search.SearchUiEvent

sealed class TVShowUiEvent {
    data class LoadPopularSeries(val page: Int): TVShowUiEvent()
    data class LoadTopRatedSeries(val page: Int): TVShowUiEvent()
    data class LoadUpcomingSeries(val page: Int): TVShowUiEvent()
}