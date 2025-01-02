package com.example.cine.session.ui.screen.serie

import com.example.cine.session.data.model.SerieInfo

data class SerieUiState (
    var serie: SerieInfo = SerieInfo(),
    val similarSeries: List<SerieInfo>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null

)