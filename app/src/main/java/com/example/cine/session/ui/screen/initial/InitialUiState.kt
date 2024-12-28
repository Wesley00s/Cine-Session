package com.example.cine.session.ui.screen.initial

data class InitialUiState(
    val firstName: String = "",
    val lastnNme: String = "",
    val password: String = "",
    val usernameError: String? = null,
    val passwordError: String? = null,
    val isLoginButtonEnabled: Boolean = false

)