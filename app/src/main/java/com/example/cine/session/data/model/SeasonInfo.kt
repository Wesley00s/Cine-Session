package com.example.cine.session.data.model

import com.example.cine.session.data.remote.response.series.EpisodeResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeasonInfo (
    @SerialName("_id") val id: String? = null,
    @SerialName("air_date") val airDate: String? = null,
    val episodes: List<EpisodeResponse>? = null,
    val name: String? = null,
    val overview: String?=  null,
    @SerialName("still_path") val posterPath: String? = null,
    @SerialName("season_number") val seasonNumber: Int? = null,
    @SerialName("vote_average") val voteAverage: Double? = null
)