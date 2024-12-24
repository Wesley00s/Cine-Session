package com.example.cine.session.data.model

import com.example.cine.session.data.remote.response.series.GenreResponse
import com.example.cine.session.data.remote.response.series.ProductionCountryResponse
import com.example.cine.session.data.remote.response.series.SeasonResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SerieInfo(
    @SerialName("backdrop_path") val backdropPath: String? = null,
    @SerialName("episode_run_time") val episodeRunTime: List<Int>,
    val genres: List<GenreResponse>,
    val homepage: String? = null,
    val id: Int,
    val name: String,
    @SerialName("number_of_episodes") val numberOfEpisodes: Int,
    @SerialName("number_of_seasons") val numberOfSeasons: Int,
    @SerialName("origin_country") val originCountry: List<String>,
    @SerialName("original_name") val originalName: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("production_countries") val productionCountries: List<ProductionCountryResponse>,
    val seasons: List<SeasonResponse>,
    val tagline: String,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("vote_count") val voteCount: Int
)
