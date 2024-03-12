package com.example.forgot_password.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.Resource
import com.core.domain.use_case.auth.GetAuthUseCase
import com.example.forgot_password.event.ForgotPasswordEvent
import com.example.forgot_password.state.ForgotPasswordState
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
class ForgotPasswordViewModel @Inject constructor(
    private val getAuthUseCase: GetAuthUseCase
) : ViewModel() {
    private val _forgotPasswordState = MutableStateFlow(ForgotPasswordState())
    val forgotPasswordState: StateFlow<ForgotPasswordState> = _forgotPasswordState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<ForgotPasswordUiEvent>()
    val uiEvent: SharedFlow<ForgotPasswordUiEvent> get() = _uiEvent

    fun onEvent(event: ForgotPasswordEvent) {
        when (event) {
            is ForgotPasswordEvent.ForgotPassword -> forgotPassword(event.email)
            ForgotPasswordEvent.ResetErrorMessage -> updateErrorMessage(message = null)
        }
    }

    private fun forgotPassword(email: String) {
        viewModelScope.launch {
            getAuthUseCase.getForgotPasswordUseCase(email).collect { resource ->
                when (resource) {
                    is Resource.Error -> updateErrorMessage(resource.errorMessage)

                    is Resource.Loading -> {
                        _forgotPasswordState.update { currentState ->
                            currentState.copy(isLoading = resource.loading)
                        }
                    }

                    is Resource.Success -> {
                        _forgotPasswordState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                errorMessage = null
                            )
                        }
                        _uiEvent.emit(ForgotPasswordUiEvent.NavigateToLogin)
                    }
                }
            }
        }
    }

    private fun updateErrorMessage(message: String?) {
        _forgotPasswordState.update { currentState -> currentState.copy(errorMessage = message) }
    }

    sealed interface ForgotPasswordUiEvent {
        data object NavigateToLogin : ForgotPasswordUiEvent
        data object NavigateToWelcome : ForgotPasswordUiEvent
    }
}