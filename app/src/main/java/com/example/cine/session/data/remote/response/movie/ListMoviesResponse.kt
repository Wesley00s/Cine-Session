package com.example.cine.session.data.remote.response.movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListMoviesResponse(
    val results: List<MovieItemResponse>
)

@Serializable
data class MovieItemResponse(
    val adult: Boolean,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("genre_ids") val genreIds: List<Int>,
    val id: Int,
    @SerialName("origin_country") val originCountry: List<String> = emptyList(),
    @SerialName("original_language") val originalLanguage: String,
    @SerialName("original_title") val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("release_date") val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("vote_count") val voteCount: Int
)