package com.example.cine.session.data.repository.implement

import com.example.cine.session.data.model.FavoriteRequest
import com.example.cine.session.data.model.FavoriteResponse
import com.example.cine.session.data.model.MovieInfo
import com.example.cine.session.data.remote.MovieRemoteDataSource
import com.example.cine.session.data.remote.response.movie.ListMoviesResponse
import com.example.cine.session.data.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {
    override suspend fun getDetailsMovies(id: Int): MovieInfo =
        movieRemoteDataSource.getDetailsMovies(id).getOrThrow()

    override suspend fun getPopularMovies(page: Int): ListMoviesResponse =
        movieRemoteDataSource.getPopularMovies(page).getOrThrow()

    override suspend fun getTopRatedMovies(page: Int): ListMoviesResponse =
        movieRemoteDataSource.getTopRatedMovies(page).getOrThrow()

    override suspend fun getFavoritesMovies(page: Int, sessionId: String): ListMoviesResponse =
        movieRemoteDataSource.getFavoritesMovies(page, sessionId).getOrThrow()

    override suspend fun postFavoriteMovie(
        sessionId: String,
        favoriteRequest: FavoriteRequest
    ): FavoriteResponse =
        movieRemoteDataSource.postFavoriteMovie(sessionId, favoriteRequest).getOrThrow()

    override suspend fun postWatchlistMovie(
        sessionId: String,
        favoriteRequest: FavoriteRequest
    ): FavoriteResponse =
        movieRemoteDataSource.postWatchlistMovie(sessionId, favoriteRequest).getOrThrow()

    override suspend fun searchMovies(query: String, page: Int): ListMoviesResponse =
        movieRemoteDataSource.searchMovies(query, page).getOrThrow()

}