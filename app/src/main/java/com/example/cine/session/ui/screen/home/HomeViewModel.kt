package com.example.cine.session.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cine.session.data.repository.MovieRepository
import com.example.cine.session.data.repository.SerieRepository
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
    private val serieRepository: SerieRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.LoadPupularMovieList -> loadPopularMovieList(event.page)
            is HomeUiEvent.LoadTopRatedMovieList -> loadTopRatedMovieList(event.page)
            is HomeUiEvent.LoadFavoritesMovieList -> loadFavoritesMovieList(
                event.page,
                event.sessionId
            )
            is HomeUiEvent.LoadPopularSeriesList -> loadPopularSeriesList(event.page)
            is HomeUiEvent.LoadUpcomingMovieList -> loadUpcomingMovieList(event.page)
            is HomeUiEvent.LoadTopRatedSeriesList -> loadTopRatedSeriesList(event.page)
        }
    }

    private fun loadPopularMovieList(page: Int) {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                currentUiState.copy(
                    popularMovies = movieRepository.getPopularMovies(page)
                )
            }
        }
    }

    private fun loadTopRatedMovieList(page: Int) {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                currentUiState.copy(
                    topRatedMovies = movieRepository.getTopRatedMovies(page)
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

    private fun loadPopularSeriesList(page: Int) {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                currentUiState.copy(
                    popularSeries = serieRepository.getPopularSeries(page)
                )
            }
        }
    }

    private fun loadUpcomingMovieList(page: Int) {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                currentUiState.copy(
                    upcomingMovies = movieRepository.getUpcomingMovies(page)
                )
            }
        }
    }

    private fun loadTopRatedSeriesList(page: Int) {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                currentUiState.copy(
                    topRatedSeries = serieRepository.getTopRatedSeries(page)
                )
            }
        }
    }
}