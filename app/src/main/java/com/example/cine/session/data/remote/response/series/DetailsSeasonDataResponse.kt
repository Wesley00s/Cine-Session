package com.example.cine.session.data.remote.response.series

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailsSeasonDataResponse(
    @SerialName("_id") val id: String,
    @SerialName("air_date") val airDate: String?,
    val episodes: List<EpisodeResponse>,
    val name: String,
    val overview: String?,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("season_number") val seasonNumber: Int,
    @SerialName("vote_average") val voteAverage: Double
)

@Serializable
data class EpisodeResponse(
    @SerialName("air_date") val airDate: String?,
    @SerialName("episode_number") val episodeNumber: Int,
    @SerialName("episode_type") val episodeType: String?,
    val id: Int,
    val name: String,
    val overview: String?,
    @SerialName("production_code") val productionCode: String?,
    val runtime: Int?,
    @SerialName("season_number") val seasonNumber: Int,
    @SerialName("show_id") val showId: Int,
    @SerialName("still_path") val stillPath: String?,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("vote_count") val voteCount: Int,
    val crew: List<CrewResponse>,
    @SerialName("guest_stars") val guestStars: List<GuestStarResponse>
)
