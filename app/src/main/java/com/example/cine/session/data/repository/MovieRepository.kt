package com.example.cine.session.data.repository

import com.example.cine.session.data.model.FavoriteRequest
import com.example.cine.session.data.model.FavoriteResponse
import com.example.cine.session.data.model.MovieInfo
import com.example.cine.session.data.remote.response.movie.ListMoviesResponse

interface MovieRepository {
    suspend fun getDetailsMovies(id: Int): MovieInfo
    suspend fun getPopularMovies(page: Int): ListMoviesResponse
    suspend fun getTopRatedMovies(page: Int): ListMoviesResponse
    suspend fun getFavoritesMovies(page: Int, sessionId: String): ListMoviesResponse
    suspend fun postFavoriteMovie(sessionId: String, favoriteRequest: FavoriteRequest): FavoriteResponse
    suspend fun postWatchlistMovie(sessionId: String, favoriteRequest: FavoriteRequest): FavoriteResponse
    suspend fun searchMovies(query: String, page: Int): ListMoviesResponse
    suspend fun getSimilarMovies(id: Int, page: Int): ListMoviesResponse
    suspend fun getUpcomingMovies(page: Int): ListMoviesResponse
}