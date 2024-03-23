package com.example.splash_screen.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.Resource
import com.core.domain.use_case.user.GetUserUseCase
import com.example.splash_screen.event.SplashScreenEvent
import com.example.splash_screen.state.SplashScreenState
import com.example.splash_screen.util.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val networkUtils: NetworkUtils
) : ViewModel() {
    private val _splashScreenState = MutableStateFlow(SplashScreenState())
    val splashScreenState: StateFlow<SplashScreenState> = _splashScreenState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<SplashScreenUiEvent>(replay = 1)
    val uiEvent: SharedFlow<SplashScreenUiEvent> get() = _uiEvent

    fun onEvent(event: SplashScreenEvent) {
        when (event) {
            SplashScreenEvent.CheckUserSessions -> checkUserSession()
            SplashScreenEvent.ResetErrorMessage -> updateErrorMessage(message = null)
        }
    }

    private fun checkUserSession() {
        viewModelScope.launch {
            if (!networkUtils.isNetworkAvailable()) {
                _uiEvent.emit(SplashScreenUiEvent.ShowNetworkError)
                return@launch
            }

            getUserUseCase.getCheckUserSessionUseCase().collect { resource ->
                when (resource) {
                    is Resource.Error -> updateErrorMessage(resource.errorMessage)

                    is Resource.Loading -> {
                        _splashScreenState.update { currentState ->
                            currentState.copy(isLoading = resource.loading)
                        }
                    }

                    is Resource.Success -> {
                        val newState = _splashScreenState.value.copy(
                            sessionIsValid = resource.data,
                            isLoading = false,
                            errorMessage = null
                        )
                        _splashScreenState.value = newState

                        if (resource.data) {
                            _uiEvent.emit(SplashScreenUiEvent.NavigateToHome)
                        } else {
                            _uiEvent.emit(SplashScreenUiEvent.NavigateToWelcome)
                        }
                    }
                }
            }
        }
    }

    private fun updateErrorMessage(message: String?) {
        _splashScreenState.update { currentState -> currentState.copy(errorMessage = message) }
    }

    sealed interface SplashScreenUiEvent {
        data object NavigateToHome : SplashScreenUiEvent
        data object NavigateToWelcome : SplashScreenUiEvent
        data object ShowNetworkError : SplashScreenUiEvent
    }
}