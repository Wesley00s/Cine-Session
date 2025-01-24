package com.example.cine.session.data.repository.implement

import com.example.cine.session.data.model.EpisodeInfo
import com.example.cine.session.data.model.FavoriteRequest
import com.example.cine.session.data.model.FavoriteResponse
import com.example.cine.session.data.model.SeasonInfo
import com.example.cine.session.data.model.SerieInfo
import com.example.cine.session.data.remote.SerieRemoteDataSource
import com.example.cine.session.data.remote.response.series.ListSeriesResponse
import com.example.cine.session.data.repository.SerieRepository
import javax.inject.Inject

class SerieRepositoryImp @Inject constructor(
    private val serieRemoteDataSource: SerieRemoteDataSource
) : SerieRepository {
    override suspend fun getDetailsSeries(id: Int): SerieInfo =
        serieRemoteDataSource.getDetailsSeries(id).getOrThrow()

    override suspend fun getPopularSeries(page: Int): ListSeriesResponse =
        serieRemoteDataSource.getPopularSeries(page).getOrThrow()

    override suspend fun getTopRatedSeries(page: Int): ListSeriesResponse =
        serieRemoteDataSource.getTopRatedSeries(page).getOrThrow()

    override suspend fun getDetailsSeason(id: Int, seasonNumber: Int): SeasonInfo =
        serieRemoteDataSource.getDetailsSeason(id, seasonNumber).getOrThrow()

    override suspend fun getSeasonEpisodes(
        id: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): EpisodeInfo =
        serieRemoteDataSource.getSeasonEpisodes(id, seasonNumber, episodeNumber).getOrThrow()

    override suspend fun getFavoriteSeries(page: Int, sessionId: String): ListSeriesResponse =
        serieRemoteDataSource.getFavoriteSeries(page, sessionId).getOrThrow()

    override suspend fun postFavoriteSerie(
        sessionId: String,
        favoriteRequest: FavoriteRequest
    ): FavoriteResponse =
        serieRemoteDataSource.postFavoriteSerie(sessionId, favoriteRequest).getOrThrow()

    override suspend fun postWatchlistSerie(
        sessionId: String,
        favoriteRequest: FavoriteRequest
    ): FavoriteResponse =
        serieRemoteDataSource.postWatchlistSerie(sessionId, favoriteRequest).getOrThrow()

    override suspend fun searchSeries(query: String, page: Int): ListSeriesResponse =
        serieRemoteDataSource.searchSeries(query, page).getOrThrow()

    override suspend fun getSimilarSeries(serieId: Int, page: Int): ListSeriesResponse =
        serieRemoteDataSource.getSimilarSeries(serieId, page).getOrThrow()

    override suspend fun getAiringTodaySeries(page: Int): ListSeriesResponse =
        serieRemoteDataSource.getAiringTodaySeries(page).getOrThrow()
}