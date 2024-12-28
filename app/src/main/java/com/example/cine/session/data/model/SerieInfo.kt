package com.example.cine.session.data.model

import com.example.cine.session.data.remote.response.movie.Genre
import com.example.cine.session.data.remote.response.series.ProductionCountryResponse
import com.example.cine.session.data.remote.response.series.SeasonResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SerieInfo(
    @SerialName("backdrop_path") val backdropPath: String? = null,
    @SerialName("episode_run_time") val episodeRunTime: List<Int>? = null,
    @SerialName("genres_ids") val genresIds: List<Int>? = emptyList(),
    @SerialName("first_air_date") val firstAirDate: String? = null,
    val genres: List<Genre>? = emptyList(),
    val homepage: String? = null,
    val id: Int? = null,
    val name: String? = null,
    @SerialName("number_of_episodes") val numberOfEpisodes: Int? = null,
    @SerialName("number_of_seasons") val numberOfSeasons: Int? = null,
    @SerialName("origin_country") val originCountry: List<String>? = null,
    @SerialName("original_name") val originalName: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("production_countries") val productionCountries: List<ProductionCountryResponse>? = null,
    val seasons: List<SeasonResponse>? = null,
    val tagline: String? = null,
    @SerialName("vote_average") val voteAverage: Double? = null,
    @SerialName("vote_count") val voteCount: Int? = null
)
