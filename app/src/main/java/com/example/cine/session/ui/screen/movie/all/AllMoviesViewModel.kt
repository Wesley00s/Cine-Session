package com.example.cine.session.ui.screen.movie.all

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cine.session.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AllMoviesViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AllMoviesUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: AllMoviesUiEvent) {
        when (event) {
            is AllMoviesUiEvent.LoadTopRatedMovies -> loadTopRatedMovies(event.page)
            is AllMoviesUiEvent.LoadPopularMovies -> loadPopularMovies(event.page)
            is AllMoviesUiEvent.LoadUpcomingMovies -> loadUpcomingMovies(event.page)
        }
    }

    private fun loadTopRatedMovies(page: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    topRatedMovies = repository.getTopRatedMovies(page)
                )
            }
        }
    }

    private fun loadPopularMovies(page: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    popularMovies = repository.getPopularMovies(page)
                )
            }
        }
    }

    private fun loadUpcomingMovies(page: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    upcomingMovies = repository.getUpcomingMovies(page)
                )
            }
        }
    }
}