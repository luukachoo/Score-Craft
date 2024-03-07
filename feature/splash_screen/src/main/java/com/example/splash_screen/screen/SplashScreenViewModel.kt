package com.example.splash_screen.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.use_case.auth.GetAuthUseCase
import com.example.splash_screen.event.SplashScreenEvent
import com.example.splash_screen.state.SplashScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val getAuthUseCase: GetAuthUseCase
): ViewModel() {
    private val _splashScreenState = MutableStateFlow(SplashScreenState())
    val splashScreenState: StateFlow<SplashScreenState> = _splashScreenState.asStateFlow()

    fun onEvent(event: SplashScreenEvent) {
        when (event) {
            SplashScreenEvent.CheckUserSessions -> TODO()
            SplashScreenEvent.ResetErrorMessage -> updateErrorMessage(message = null)
        }
    }

    private fun checkUserSession() {
        viewModelScope.launch {

        }
    }

    private fun updateErrorMessage(message: String?) {
        _splashScreenState.update { currentState -> currentState.copy(errorMessage = message) }
    }

    sealed interface ProfileUiEvent {
        data object NavigateToHome : ProfileUiEvent
        data object NavigateToWelcome: ProfileUiEvent
    }
}