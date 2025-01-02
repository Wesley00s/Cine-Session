package com.example.cine.session.ui.screen.serie.season

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cine.session.data.repository.SeasonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeasonViewModel @Inject constructor(
    private val seasonRepository: SeasonRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SeasonUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: SeasonUiEvent) {
        when (event) {
            is SeasonUiEvent.LoadSeason -> loadSeason(event.serieId, event.seasonNumber)
            SeasonUiEvent.ClearSeason -> clearSeason()
        }
    }

    private fun loadSeason(serieId: Int, seasonNumber: Int) {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                currentUiState.copy(
                    season = seasonRepository.getDetailsSeason(serieId, seasonNumber)
                )
            }
        }
    }

    private fun clearSeason() {
        _uiState.update { currentUiState ->
            currentUiState.copy(
                season = null
            )
        }
    }
}