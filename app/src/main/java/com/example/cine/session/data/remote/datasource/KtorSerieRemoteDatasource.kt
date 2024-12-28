package com.example.cine.session.data.remote.datasource

import com.example.cine.session.BuildConfig
import com.example.cine.session.data.model.EpisodeInfo
import com.example.cine.session.data.model.FavoriteRequest
import com.example.cine.session.data.model.FavoriteResponse
import com.example.cine.session.data.model.SeasonInfo
import com.example.cine.session.data.model.SerieInfo
import com.example.cine.session.data.remote.SerieRemoteDataSource
import com.example.cine.session.data.remote.response.series.ListSeriesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class KtorSerieRemoteDatasource @Inject constructor(
    private val httpClient: HttpClient
) : SerieRemoteDataSource {
    companion object {
        const val BASE_URL = BuildConfig.BASE_URL
        const val TOKEN_API = BuildConfig.API_KEY
        const val ACCOUNT_ID = BuildConfig.ACCOUNT_ID
    }

    override suspend fun getDetailsSeries(id: Int): Result<SerieInfo> = try {
        val response = httpClient
            .get("$BASE_URL/tv/$id?language=en-US") {
                header("Authorization", "Bearer $TOKEN_API")
                header("accept", "application/json")
            }
            .body<SerieInfo>()
        Result.success(response)

    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getPopularSeries(page: Int): Result<ListSeriesResponse> = try {
        val response = httpClient
            .get("$BASE_URL/tv/popular?language=en-US&page=$page") {
                header("Authorization", "Bearer $TOKEN_API")
                header("accept", "application/json")
            }
            .body<ListSeriesResponse>()
        Result.success(response)

    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getTopRatedSeries(page: Int): Result<ListSeriesResponse> =
        try {
            val response = httpClient
                .get("$BASE_URL/tv/top_rated?language=en-US&page=$page") {
                    header("Authorization", "Bearer $TOKEN_API")
                    header("accept", "application/json")
                }
                .body<ListSeriesResponse>()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }

    override suspend fun getDetailsSeason(
        id: Int,
        seasonNumber: Int
    ): Result<SeasonInfo> = try {
        val response = httpClient
            .get("$BASE_URL/tv/$id/season/$seasonNumber?language=en-US") {
                header("Authorization", "Bearer $TOKEN_API")
                header("accept", "application/json")
            }
            .body<SeasonInfo>()
        Result.success(response)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getSeasonEpisodes(
        id: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): Result<EpisodeInfo> = try {
        val response = httpClient
            .get("$BASE_URL/tv/$id/season/$seasonNumber/episode/$episodeNumber?language=en-US") {
                header("Authorization", "Bearer $TOKEN_API")
                header("accept", "application/json")
            }
            .body<EpisodeInfo>()
        Result.success(response)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getFavoriteSeries(
        page: Int,
        sessionId: String
    ): Result<ListSeriesResponse> = try {
        val response = httpClient
            .get("$BASE_URL/account/$ACCOUNT_ID/favorite/movies?language=en-US&page=$page&session_id=$sessionId") {
                header("Authorization", "Bearer $TOKEN_API")
                header("accept", "application/json")
            }
            .body<ListSeriesResponse>()
        Result.success(response)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun postFavoriteSerie(
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

    override suspend fun postWatchlistSerie(
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

    override suspend fun searchSeries(query: String, page: Int): Result<ListSeriesResponse> = try {
        val response = httpClient
            .get("$BASE_URL/search/tv?query=$query&page=$page&include_adult=false&language=en-US") {
                header("Authorization", "Bearer $TOKEN_API")
                header("accept", "application/json")
            }
            .body<ListSeriesResponse>()
        Result.success(response)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getSimilarSeries(serieId: Int, page: Int): Result<ListSeriesResponse> =
        try {
            val response = httpClient
                .get("$BASE_URL/tv/$serieId/similar?language=en-US&page=$page") {
                    header("Authorization", "Bearer $TOKEN_API")
                    header("accept", "application/json")
                }
                .body<ListSeriesResponse>()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
}