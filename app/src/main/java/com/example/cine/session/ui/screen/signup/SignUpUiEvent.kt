package com.example.cine.session.ui.screen.signup

sealed class SignUpUiEvent {
    data class Register(
        val firstName: String,
        val lastName: String,
        val email: String,
        val password: String
    ) : SignUpUiEvent()
}