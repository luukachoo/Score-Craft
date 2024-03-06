package com.example.login.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.Resource
import com.core.domain.use_case.auth.GetAuthUseCase
import com.core.domain.use_case.validation.ValidationUseCase
import com.example.login.event.LoginEvent
import com.example.login.state.LoginState
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
class LoginFragmentViewModel @Inject constructor(
    private val getAuthUseCase: GetAuthUseCase,
    private val validationUseCase: ValidationUseCase
) : ViewModel() {
    private val _logInState = MutableStateFlow(LoginState())
    val logInState: StateFlow<LoginState> = _logInState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<LogInUiEvent>()
    val uiEvent: SharedFlow<LogInUiEvent> get() = _uiEvent

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.LogIn -> validateForm(email = event.email, password = event.password)
            LoginEvent.ResetErrorMessage -> updateErrorMessage(message = null)
        }
    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            getAuthUseCase.getLoginUseCase(email, password).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _logInState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                firebaseUser = resource.data,
                                errorMessage = null
                            )
                        }
                        _uiEvent.emit(LogInUiEvent.NavigateToHome)
                    }

                    is Resource.Error -> updateErrorMessage(resource.errorMessage)

                    is Resource.Loading -> {
                        _logInState.update { currentState ->
                            currentState.copy(isLoading = resource.loading)
                        }
                    }
                }
            }
        }
    }

    private fun validateForm(email: String, password: String) {
        val isPasswordValid = validationUseCase.fieldValidatorUseCase(listOf(password))
        val isEmailValid = email.isNotBlank()

        val areFieldsValid = listOf(isEmailValid, isPasswordValid).all { it }

        if (!areFieldsValid) {
            updateErrorMessage(message = "Fields are not valid!")
            return
        }

        _logInState.update { it.copy(isLoading = true) }
        login(email = email, password = password)
    }

    private fun updateErrorMessage(message: String?) {
        _logInState.update { currentState -> currentState.copy(errorMessage = message) }
    }

    fun navigateToForgotPasswordPage() {
        viewModelScope.launch {
            _uiEvent.emit(LogInUiEvent.NavigateToForgotPasswordPage)
        }
    }

    fun navigateToRegister() {
        viewModelScope.launch {
            _uiEvent.emit(LogInUiEvent.NavigateToRegister)
        }
    }

    sealed interface LogInUiEvent {
        data object NavigateToRegister : LogInUiEvent
        data object NavigateToHome : LogInUiEvent
        data object NavigateToForgotPasswordPage : LogInUiEvent
    }
}