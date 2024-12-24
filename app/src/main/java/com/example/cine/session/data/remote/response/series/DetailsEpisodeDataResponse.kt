package com.example.cine.session.data.remote.response.series

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailsEpisodeDataResponse(
    @SerialName("air_date") val airDate: String?,
    val crew: List<CrewResponse>,
    @SerialName("episode_number") val episodeNumber: Int,
    @SerialName("guest_stars") val guestStars: List<GuestStarResponse>,
    val name: String,
    val overview: String?,
    val id: Int,
    @SerialName("production_code") val productionCode: String?,
    val runtime: Int?,
    @SerialName("season_number") val seasonNumber: Int,
    @SerialName("still_path") val stillPath: String?,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("vote_count") val voteCount: Int
)