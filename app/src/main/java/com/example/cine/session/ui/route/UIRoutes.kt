package com.example.cine.session.ui.route

import kotlinx.serialization.Serializable

@Serializable
data object Splash

@Serializable
data object Login

@Serializable
data object SignUp

@Serializable
data object Initial

@Serializable
data class Movie(val id: Int)

@Serializable
data class Serie(val id: Int)

@Serializable
data object Home