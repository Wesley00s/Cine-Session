package com.example.cine.session.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cine.session.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.LoadPupularMovieList -> loadPopularMovieList()
        }
    }

    private fun loadPopularMovieList() {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                currentUiState.copy(
                    movies = movieRepository.getPopularMovies(1)
                )
            }
        }

        Log.d("PopularMovies_ViewModel", "Response:" + _uiState.value.movies.toString())
    }

}