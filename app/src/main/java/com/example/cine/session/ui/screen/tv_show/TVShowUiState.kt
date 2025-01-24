package com.example.cine.session.ui.screen.tv_show

import com.example.cine.session.data.remote.response.series.ListSeriesResponse

data class TVShowUiState(
    val popularSeries: ListSeriesResponse? = null,
    val topRatedSeries: ListSeriesResponse? = null,
    val upcomingSeries: ListSeriesResponse? = null,
    val favoritesSeries: ListSeriesResponse? = null
)