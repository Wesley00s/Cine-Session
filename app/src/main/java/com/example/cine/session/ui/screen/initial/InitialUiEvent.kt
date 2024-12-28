package com.example.cine.session.ui.screen.initial

sealed class InitialUiEvent {
    data class Initial(val email: String, val password: String) : InitialUiEvent()

}