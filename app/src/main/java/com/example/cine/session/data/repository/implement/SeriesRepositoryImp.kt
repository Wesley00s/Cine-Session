package com.example.cine.session.data.repository.implement

import com.example.cine.session.data.model.EpisodeInfo
import com.example.cine.session.data.model.FavoriteRequest
import com.example.cine.session.data.model.FavoriteResponse
import com.example.cine.session.data.model.SeasonInfo
import com.example.cine.session.data.model.SerieInfo
import com.example.cine.session.data.remote.SerieRemoteDataSource
import com.example.cine.session.data.remote.response.series.ListSeriesResponse
import com.example.cine.session.data.repository.SeriesRepository
import javax.inject.Inject

class SeriesRepositoryImp @Inject constructor(
    private val serieRremoteDataSource: SerieRemoteDataSource
) : SeriesRepository {
    override suspend fun getDetailsSeries(id: Int): SerieInfo =
        serieRremoteDataSource.getDetailsSeries(id).getOrThrow()

    override suspend fun getPopularSeries(page: Int): ListSeriesResponse =
        serieRremoteDataSource.getPopularSeries(page).getOrThrow()

    override suspend fun getTopRatedSeries(page: Int): ListSeriesResponse =
        serieRremoteDataSource.getTopRatedSeries(page).getOrThrow()

    override suspend fun getDetailsSeason(id: Int, seasonNumber: Int): SeasonInfo =
        serieRremoteDataSource.getDetailsSeason(id, seasonNumber).getOrThrow()

    override suspend fun getSeasonEpisodes(
        id: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): EpisodeInfo =
        serieRremoteDataSource.getSeasonEpisodes(id, seasonNumber, episodeNumber).getOrThrow()

    override suspend fun getFavoriteSeries(page: Int, sessionId: String): ListSeriesResponse =
        serieRremoteDataSource.getFavoriteSeries(page, sessionId).getOrThrow()

    override suspend fun postFavoriteSerie(
        sessionId: String,
        favoriteRequest: FavoriteRequest
    ): FavoriteResponse =
        serieRremoteDataSource.postFavoriteSerie(sessionId, favoriteRequest).getOrThrow()

    override suspend fun postWatchlistSerie(
        sessionId: String,
        favoriteRequest: FavoriteRequest
    ): FavoriteResponse =
        serieRremoteDataSource.postWatchlistSerie(sessionId, favoriteRequest).getOrThrow()

    override suspend fun searchSeries(query: String, page: Int): ListSeriesResponse =
        serieRremoteDataSource.searchSeries(query, page).getOrThrow()
}