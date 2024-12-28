package com.example.cine.session.ui.screen.serie

sealed class SerieUiEvent {
    data class LoadSerie(val serieId: Int) : SerieUiEvent()
    data class LoadSimilarSeries(val serieId: Int, val page: Int) : SerieUiEvent()

}