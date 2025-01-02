package com.example.cine.session.ui.screen.serie.season

sealed class SeasonUiEvent{
    data class LoadSeason(val serieId: Int, val seasonNumber: Int) : SeasonUiEvent()
    data object ClearSeason: SeasonUiEvent()
}