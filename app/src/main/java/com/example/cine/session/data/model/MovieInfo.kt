package com.example.cine.session.data.model

import com.example.cine.session.data.remote.response.movie.Genre
import com.example.cine.session.data.remote.response.movie.ProductionCountry
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieInfo(
    @SerialName("backdrop_path") val backdropPath: String? = null,
    val genres: List<Genre>? = emptyList(),
    val homepage: String? = null,
    val id: Int? = null,
    @SerialName("origin_country") val originCountry: List<String>? = emptyList(),
    @SerialName("original_title") val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("production_countries") val productionCountries: List<ProductionCountry>? = emptyList(),
    @SerialName("release_date") val releaseDate: String? = null,
    val runtime: Int? = null,
    val title: String? = null,
    @SerialName("vote_average") val voteAverage: Double? = null,
    @SerialName("vote_count") val voteCount: Int? = null
)