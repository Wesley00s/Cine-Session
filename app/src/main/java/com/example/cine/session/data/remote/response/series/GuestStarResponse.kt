package com.example.cine.session.data.remote.response.series

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuestStarResponse(
    val character: String,
    @SerialName("credit_id") val creditId: String,
    val order: Int,
    val adult: Boolean,
    val gender: Int?,
    val id: Int,
    @SerialName("known_for_department") val knownForDepartment: String,
    val name: String,
    @SerialName("original_name") val originalName: String,
    val popularity: Double,
    @SerialName("profile_path") val profilePath: String?
)
