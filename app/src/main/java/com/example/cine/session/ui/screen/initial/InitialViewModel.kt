package com.example.cine.session.ui.screen.initial

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class InitialViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(InitialUiState())
    val uiState: StateFlow<InitialUiState> = _uiState.asStateFlow()

    fun onEvent(event: InitialUiEvent) {

    }

}