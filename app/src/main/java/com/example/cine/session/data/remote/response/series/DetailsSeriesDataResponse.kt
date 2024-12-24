package com.example.cine.session.data.remote.response.series

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailsSeriesDataResponse(
    val adult: Boolean,
    @SerialName("backdrop_path") val backdropPath: String? = null,
    @SerialName("created_by") val createdBy: List<CreatorResponse>,
    @SerialName("episode_run_time") val episodeRunTime: List<Int>,
    @SerialName("first_air_date") val firstAirDate: String? = null,
    val genres: List<GenreResponse>,
    val homepage: String? = null,
    val id: Int,
    @SerialName("in_production") val inProduction: Boolean,
    val languages: List<String>,
    @SerialName("last_air_date") val lastAirDate: String? = null,
    @SerialName("last_episode_to_air") val lastEpisodeToAir: LastEpisodeResponse? = null,
    val name: String,
    @SerialName("next_episode_to_air") val nextEpisodeToAir: LastEpisodeResponse?,
    val networks: List<NetworkResponse>,
    @SerialName("number_of_episodes") val numberOfEpisodes: Int,
    @SerialName("number_of_seasons") val numberOfSeasons: Int,
    @SerialName("origin_country") val originCountry: List<String>,
    @SerialName("original_language") val originalLanguage: String,
    @SerialName("original_name") val originalName: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("production_companies") val productionCompanies: List<ProductionCompanyResponse>,
    @SerialName("production_countries") val productionCountries: List<ProductionCountryResponse>,
    val seasons: List<SeasonResponse>,
    @SerialName("spoken_languages") val spokenLanguages: List<SpokenLanguageResponse>,
    val status: String,
    val tagline: String,
    val type: String,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("vote_count") val voteCount: Int
)

@Serializable
data class CreatorResponse(
    val id: Int,
    @SerialName("credit_id") val creditId: String,
    val name: String,
    @SerialName("original_name") val originalName: String,
    val gender: Int,
    @SerialName("profile_path") val profilePath: String? = null
)

@Serializable
data class GenreResponse(
    val id: Int,
    val name: String
)

@Serializable
data class LastEpisodeResponse(
    val id: Int,
    val name: String,
    val overview: String,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("vote_count") val voteCount: Int,
    @SerialName("air_date") val airDate: String,
    @SerialName("episode_number") val episodeNumber: Int,
    @SerialName("episode_type") val episodeType: String? = null,
    @SerialName("production_code") val productionCode: String? = null,
    val runtime: Int? = null,
    @SerialName("season_number") val seasonNumber: Int,
    @SerialName("show_id") val showId: Int,
    @SerialName("still_path") val stillPath: String? = null
)

@Serializable
data class NetworkResponse(
    val id: Int,
    @SerialName("logo_path") val logoPath: String? = null,
    val name: String,
    @SerialName("origin_country") val originCountry: String
)

@Serializable
data class ProductionCompanyResponse(
    val id: Int,
    @SerialName("logo_path") val logoPath: String? = null,
    val name: String,
    @SerialName("origin_country") val originCountry: String
)

@Serializable
data class ProductionCountryResponse(
    @SerialName("iso_3166_1") val iso3166_1: String,
    val name: String
)

@Serializable
data class SeasonResponse(
    @SerialName("air_date") val airDate: String? = null,
    @SerialName("episode_count") val episodeCount: Int,
    val id: Int,
    val name: String,
    val overview: String?,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("season_number") val seasonNumber: Int,
    @SerialName("vote_average") val voteAverage: Double
)

@Serializable
data class SpokenLanguageResponse(
    @SerialName("english_name") val englishName: String,
    @SerialName("iso_639_1") val iso639_1: String,
    val name: String
)
