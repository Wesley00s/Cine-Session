package com.example.cine.session.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeInfo (
    @SerialName("air_date") val airDate: String?,
    @SerialName("episode_number") val episodeNumber: Int,
    val name: String,
    val overview: String?,
    val id: Int,
    val runtime: Int?,
    @SerialName("season_number") val seasonNumber: Int,
    @SerialName("still_path") val stillPath: String?,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("vote_count") val voteCount: Int
)