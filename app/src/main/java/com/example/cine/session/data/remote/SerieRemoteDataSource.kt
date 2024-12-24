package com.example.cine.session.data.remote

import com.example.cine.session.data.model.EpisodeInfo
import com.example.cine.session.data.model.FavoriteRequest
import com.example.cine.session.data.model.FavoriteResponse
import com.example.cine.session.data.model.SeasonInfo
import com.example.cine.session.data.model.SerieInfo
import com.example.cine.session.data.remote.response.series.ListSeriesResponse

interface SerieRemoteDataSource {
    suspend fun getDetailsSeries(id: Int): Result<SerieInfo>
    suspend fun getPopularSeries(page: Int): Result<ListSeriesResponse>
    suspend fun getTopRatedSeries(page: Int): Result<ListSeriesResponse>
    suspend fun getDetailsSeason(id: Int, seasonNumber: Int): Result<SeasonInfo>
    suspend fun getSeasonEpisodes(id: Int, seasonNumber: Int, episodeNumber: Int): Result<EpisodeInfo>
    suspend fun getFavoriteSeries(page: Int, sessionId: String): Result<ListSeriesResponse>
    suspend fun postFavoriteSerie(sessionId: String, favoriteRequest: FavoriteRequest): Result<FavoriteResponse>
    suspend fun postWatchlistSerie(sessionId: String, favoriteRequest: FavoriteRequest): Result<FavoriteResponse>
    suspend fun searchSeries(query: String, page: Int): Result<ListSeriesResponse>
}