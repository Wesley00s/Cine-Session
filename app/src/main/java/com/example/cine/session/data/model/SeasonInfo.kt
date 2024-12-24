package com.example.cine.session.data.model

import com.example.cine.session.data.remote.response.series.EpisodeResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeasonInfo (
    @SerialName("_id") val id: String,
    @SerialName("air_date") val airDate: String?,
    val episodes: List<EpisodeResponse>,
    val name: String,
    val overview: String?,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("season_number") val seasonNumber: Int,
    @SerialName("vote_average") val voteAverage: Double
)