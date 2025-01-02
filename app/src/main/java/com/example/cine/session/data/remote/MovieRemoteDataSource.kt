package com.example.cine.session.data.remote

import com.example.cine.session.data.model.FavoriteRequest
import com.example.cine.session.data.model.FavoriteResponse
import com.example.cine.session.data.model.MovieInfo
import com.example.cine.session.data.remote.response.movie.ListMoviesResponse

interface MovieRemoteDataSource {
    suspend fun getDetailsMovies(id: Int): Result<MovieInfo>
    suspend fun getPopularMovies(page: Int): Result<ListMoviesResponse>
    suspend fun getTopRatedMovies(page: Int): Result<ListMoviesResponse>
    suspend fun getFavoritesMovies(page: Int, sessionId: String): Result<ListMoviesResponse>
    suspend fun postFavoriteMovie(sessionId: String, favoriteRequest: FavoriteRequest): Result<FavoriteResponse>
    suspend fun postWatchlistMovie(sessionId: String, favoriteRequest: FavoriteRequest): Result<FavoriteResponse>
    suspend fun searchMovies(query: String, page: Int): Result<ListMoviesResponse>
    suspend fun getSimilarMovies(id: Int, page: Int): Result<ListMoviesResponse>
    suspend fun getUpcomingMovies(page: Int): Result<ListMoviesResponse>
}