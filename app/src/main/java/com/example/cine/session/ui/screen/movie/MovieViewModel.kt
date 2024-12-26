package com.example.cine.session.ui.screen.movie

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cine.session.data.model.MovieInfo
import com.example.cine.session.data.model.enumeration.MappedGenre
import com.example.cine.session.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(MovieUiState())
    val uiState: StateFlow<MovieUiState> = _uiState.asStateFlow()

    fun onEvent(event: MovieUiEvent) {
        when (event) {
            is MovieUiEvent.LoadMovie -> loadMovie(event.movieId)
            is MovieUiEvent.LoadSimilarMovies -> loadSimilarMovies(event.movieId, event.page)
        }
    }

    private fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                currentUiState.copy(
                    movie = movieRepository.getDetailsMovies(movieId)
                )
            }
        }
    }

    private fun loadSimilarMovies(movieId: Int, page: Int) {
        viewModelScope.launch {
            _uiState.update { currentUiState ->

                val similarMovies =
                    movieRepository.getSimilarMovies(movieId, page).results.map {
                        MovieInfo(
                            id = it.id,
                            posterPath = it.posterPath,
                            title = it.title,
                            overview = it.overview,
                            releaseDate = it.releaseDate,
                            voteAverage = it.voteAverage,
                            voteCount = it.voteCount,
                            backdropPath = it.backdropPath,
                            originalTitle = it.originalTitle,
                            popularity = it.popularity,
                            genres = it.genreIds.map { id -> MappedGenre.fromId(id) }
                        )
                    }

                Log.d("SimilarMovies_ViewModel", "Response:$similarMovies")

                currentUiState.copy(
                    similarMovies = similarMovies
                )
            }
        }
    }
}