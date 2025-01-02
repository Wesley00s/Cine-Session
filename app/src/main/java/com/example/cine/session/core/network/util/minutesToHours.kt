package com.example.cine.session.core.network.util

fun minutesToHours(minutes: Int): String {
    val hours = minutes / 60
    val remainingMinutes = minutes % 60
    if (hours == 0) return "${remainingMinutes}min"
    return "${hours}h ${remainingMinutes}min"
}