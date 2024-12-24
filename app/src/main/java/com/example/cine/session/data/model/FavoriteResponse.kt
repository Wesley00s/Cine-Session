package com.example.cine.session.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavoriteResponse (
    val success: String,
    @SerialName("status_code") val statusCode: Int,
    @SerialName("status_message") val statusMessage: String,
)