package com.example.cine.session.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cine.session.data.repository.MovieRepository
import com.example.cine.session.data.repository.SeriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.LoadPupularMovieList -> loadPopularMovieList()
            is HomeUiEvent.LoadTopRatedMovieList -> loadTopRatedMovieList()
            is HomeUiEvent.LoadFavoritesMovieList -> loadFavoritesMovieList(
                event.page,
                event.sessionId
            )

            is HomeUiEvent.LoadPopularSeriesList -> loadPopularSeriesList()
        }
    }

    private fun loadPopularMovieList() {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                currentUiState.copy(
                    popularMovies = movieRepository.getPopularMovies(1)
                )
            }
        }
    }

    private fun loadTopRatedMovieList() {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                currentUiState.copy(
                    topRatedMovies = movieRepository.getTopRatedMovies(1)
                )
            }
        }
    }

    private fun loadFavoritesMovieList(page: Int = 1, sessionId: String = "7db984741d1d3e7d21ef85e902cc935cc6a71887") {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                currentUiState.copy(
                    favoritesMovies = movieRepository.getFavoritesMovies(page, sessionId)
                )
            }
        }
    }

    private fun loadPopularSeriesList() {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                currentUiState.copy(
                    popularSeries = seriesRepository.getPopularSeries(1)
                )
            }
        }
    }
}