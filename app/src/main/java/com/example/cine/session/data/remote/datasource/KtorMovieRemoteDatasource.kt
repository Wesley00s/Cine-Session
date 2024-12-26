package com.example.cine.session.data.remote.datasource

import com.example.cine.session.BuildConfig
import com.example.cine.session.core.network.KtorHttpClient
import com.example.cine.session.data.model.FavoriteRequest
import com.example.cine.session.data.model.FavoriteResponse
import com.example.cine.session.data.model.MovieInfo
import com.example.cine.session.data.model.SeasonInfo
import com.example.cine.session.data.remote.MovieRemoteDataSource
import com.example.cine.session.data.remote.response.movie.ListMoviesResponse
import com.example.cine.session.data.remote.response.series.ListSeriesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class KtorMovieRemoteDatasource @Inject constructor(
    private val httpClient: HttpClient
) : MovieRemoteDataSource {
    companion object {
        private val httpClient: KtorHttpClient = KtorHttpClient
        const val BASE_URL = BuildConfig.BASE_URL
        const val TOKEN_API = BuildConfig.API_KEY
        const val ACCOUNT_ID = BuildConfig.ACCOUNT_ID
    }

    override suspend fun getDetailsMovies(id: Int): Result<MovieInfo> = try {
        val response = httpClient
            .get("$BASE_URL/movie/$id?language=en-US") {
                header("Authorization", "Bearer $TOKEN_API")
                header("accept", "application/json")
            }
            .body<MovieInfo>()
        Result.success(response)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getPopularMovies(page: Int): Result<ListMoviesResponse> = try {
        val response = httpClient
            .get("$BASE_URL/movie/popular?language=en-US&page=$page") {
                header("Authorization", "Bearer $TOKEN_API")
                header("accept", "application/json")
            }
            .body<ListMoviesResponse>()
        Result.success(response)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getTopRatedMovies(page: Int): Result<ListMoviesResponse> =
        try {
            val response = httpClient
                .get("$BASE_URL/movie/top_rated?language=en-US&page=$page") {
                    header("Authorization", "Bearer $TOKEN_API")
                    header("accept", "application/json")
                }
                .body<ListMoviesResponse>()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }

    override suspend fun getFavoritesMovies(
        page: Int,
        sessionId: String
    ): Result<ListMoviesResponse> = try {
        val response = httpClient
            .get("$BASE_URL/account/$ACCOUNT_ID/favorite/movies?language=en-US&page=$page&session_id=$sessionId") {
                header("Authorization", "Bearer $TOKEN_API")
                header("accept", "application/json")
            }
            .body<ListMoviesResponse>()
        Result.success(response)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun postFavoriteMovie(
        sessionId: String,
        favoriteRequest: FavoriteRequest
    ): Result<FavoriteResponse> = try {
        val response = httpClient
            .post("$BASE_URL/account/$ACCOUNT_ID/favorite?session_id=$sessionId") {
                header("Authorization", "Bearer $TOKEN_API")
                header("accept", "application/json")
                header("Content-Type", "application/json")
                setBody(favoriteRequest)
            }
            .body<FavoriteResponse>()
        Result.success(response)

    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun postWatchlistMovie(
        sessionId: String,
        favoriteRequest: FavoriteRequest
    ): Result<FavoriteResponse> = try {
        val response = httpClient
            .post("$BASE_URL/account/$ACCOUNT_ID/watchlist?session_id=$sessionId") {
                header("Authorization", "Bearer $TOKEN_API")
                header("accept", "application/json")
                header("Content-Type", "application/json")
                setBody(favoriteRequest)
            }
            .body<FavoriteResponse>()
        Result.success(response)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun searchMovies(query: String, page: Int): Result<ListMoviesResponse> = try {
        val response = httpClient
            .get("$BASE_URL/search/movie?query=$query&page=$page&include_adult=false&language=en-US") {
                header("Authorization", "Bearer $TOKEN_API")
                header("accept", "application/json")
            }
            .body<ListMoviesResponse>()
        Result.success(response)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getSimilarMovies(id: Int, page: Int): Result<ListMoviesResponse> = try {
        val response = httpClient
            .get("$BASE_URL/movie/$id/similar?language=en-US&page=$page") {
                header("Authorization", "Bearer $TOKEN_API")
                header("accept", "application/json")
            }
            .body<ListMoviesResponse>()
        Result.success(response)
    } catch (e: Exception) {
        Result.failure(e)
    }
}