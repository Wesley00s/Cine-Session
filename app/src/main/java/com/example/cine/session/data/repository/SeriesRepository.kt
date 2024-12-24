package com.example.cine.session.data.repository

import com.example.cine.session.data.model.EpisodeInfo
import com.example.cine.session.data.model.FavoriteRequest
import com.example.cine.session.data.model.FavoriteResponse
import com.example.cine.session.data.model.SeasonInfo
import com.example.cine.session.data.model.SerieInfo
import com.example.cine.session.data.remote.response.series.ListSeriesResponse

interface SeriesRepository {
    suspend fun getDetailsSeries(id: Int): SerieInfo
    suspend fun getPopularSeries(page: Int): ListSeriesResponse
    suspend fun getTopRatedSeries(page: Int): ListSeriesResponse
    suspend fun getDetailsSeason(id: Int, seasonNumber: Int): SeasonInfo
    suspend fun getSeasonEpisodes(id: Int, seasonNumber: Int, episodeNumber: Int): EpisodeInfo
    suspend fun getFavoriteSeries(page: Int, sessionId: String): ListSeriesResponse
    suspend fun postFavoriteSerie(sessionId: String, favoriteRequest: FavoriteRequest): FavoriteResponse
    suspend fun postWatchlistSerie(sessionId: String, favoriteRequest: FavoriteRequest): FavoriteResponse
    suspend fun searchSeries(query: String, page: Int): ListSeriesResponse

}