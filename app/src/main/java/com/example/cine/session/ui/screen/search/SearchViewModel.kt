package com.example.cine.session.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cine.session.data.model.MovieInfo
import com.example.cine.session.data.model.SerieInfo
import com.example.cine.session.data.model.enumeration.MappedGenre
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
class SearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val serieRepository: SerieRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun onEvent(event: SearchUiEvent) {
        when (event) {
            is SearchUiEvent.SearchMovies -> searchMovies(event.query, event.page)
            is SearchUiEvent.SearchSeries -> searchSeries(event.query, event.page)
        }
    }

    private fun searchMovies(query: String, page: Int) {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                currentUiState.copy(
                    movies = movieRepository.searchMovies(query, page).results.map {
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
                )
            }
        }
    }

    private fun searchSeries(query: String, page: Int) {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                currentUiState.copy(
                    series = serieRepository.searchSeries(query, page).results.map {
                        SerieInfo(
                            id = it.id,
                            posterPath = it.posterPath,
                            name = it.name,
                            overview = it.overview,
                            firstAirDate = it.firstAirDate,
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