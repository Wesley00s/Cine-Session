package com.example.cine.session.ui.screen.search

import com.example.cine.session.data.model.MovieInfo
import com.example.cine.session.data.model.SerieInfo

data class SearchUiState (
    val movies: List<MovieInfo>? = null,
    val series: List<SerieInfo>? = null,

    )