package com.example.cine.session.ui.screen.login

import com.example.cine.session.ui.screen.initial.InitialUiEvent

sealed class LoginUiEvent {
    data class Login(val email: String, val password: String) : LoginUiEvent()

}