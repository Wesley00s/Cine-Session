package com.example.cine.session.ui.screen.tv_show

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cine.session.data.repository.SerieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVShowViewModel @Inject constructor(
    private val serieRepository: SerieRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TVShowUiState())
    val uiState: StateFlow<TVShowUiState> = _uiState.asStateFlow()

    fun onEvent(event: TVShowUiEvent) {
        when (event) {
            is TVShowUiEvent.LoadPopularSeries -> loadPopularSeries(event.page)
            is TVShowUiEvent.LoadTopRatedSeries -> loadTopRatedSeries(event.page)
            is TVShowUiEvent.LoadAiringTodaySeries -> loadAiringTodaySeries(event.page)
        }
    }

    private fun loadPopularSeries(page: Int) {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                currentUiState.copy(
                    popularSeries = serieRepository.getPopularSeries(page)
                )
            }
        }
    }

    private fun loadTopRatedSeries(page: Int) {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                currentUiState.copy(
                    topRatedSeries = serieRepository.getTopRatedSeries(page)
                )

            }
        }
    }

    private fun loadAiringTodaySeries(page: Int) {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                currentUiState.copy(
                    upcomingSeries = serieRepository.getAiringTodaySeries(page)
                )
            }
        }
    }
}

