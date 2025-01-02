package com.example.cine.session.ui.screen.serie.season

import com.example.cine.session.data.model.EpisodeInfo
import com.example.cine.session.data.model.SeasonInfo

data class SeasonUiState(
    var season: SeasonInfo? = null,
    val episodes: List<EpisodeInfo>? = null
)