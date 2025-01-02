package com.example.cine.session.ui.screen.serie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cine.session.data.model.SerieInfo
import com.example.cine.session.data.model.enumeration.MappedGenre
import com.example.cine.session.data.repository.SerieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SerieViewModel @Inject constructor(
    private val repository: SerieRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SerieUiState())
    val uiState: StateFlow<SerieUiState> = _uiState.asStateFlow()

    fun onEvent(event: SerieUiEvent) {
        when (event) {
            is SerieUiEvent.LoadSerie -> loadSerie(event.serieId)
            is SerieUiEvent.LoadSimilarSeries -> loadSimilarSeries(event.serieId, event.page)
        }
    }

    private fun loadSerie(serieId: Int) {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                currentUiState.copy(
                    serie = repository.getDetailsSeries(serieId)
                )
            }
        }
    }

    private fun loadSimilarSeries(serieId: Int, page: Int) {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                currentUiState.copy(
                    similarSeries = repository.getSimilarSeries(serieId, page).results.map {
                        SerieInfo(
                            id = it.id,
                            posterPath = it.posterPath,
                            name = it.name,
                            firstAirDate = it.firstAirDate,
                            overview = it.overview,
                            voteAverage = it.voteAverage,
                            voteCount = it.voteCount,
                            backdropPath = it.backdropPath,
                            originalName = it.originalName,
                            popularity = it.popularity,
                            genres = it.genreIds.map { id -> MappedGenre.fromId(id) }
                        )
                    }
                )
            }
        }
    }
}