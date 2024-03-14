package com.example.registration.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.Resource
import com.core.domain.use_case.auth.GetAuthUseCase
import com.core.domain.use_case.validation.ValidationUseCase
import com.example.registration.event.RegisterEvent
import com.example.registration.state.RegisterState
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
class RegisterFragmentViewModel @Inject constructor(
    private val getAuthUseCase: GetAuthUseCase,
    private val validationUseCase: ValidationUseCase
) : ViewModel() {
    private val _registerState = MutableStateFlow(RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<RegisterUiEvent>()
    val uiEvent: SharedFlow<RegisterUiEvent> get() = _uiEvent

    fun onEvent(event: RegisterEvent) {
        when (event) {
            RegisterEvent.ResetErrorMessage -> updateErrorMessage(message = null)

            is RegisterEvent.Register -> validateForm(
                userName = event.userName,
                firstName = event.firstName,
                lastName = event.lastName,
                email = event.email,
                password = event.password,
                confirmPassword = event.confirmPassword
            )
        }
    }

    private fun register(
        userName: String,
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            getAuthUseCase.getRegisterUseCase(userName, firstName, lastName, email, password)
                .collect { resource ->
                    when (resource) {
                        is Resource.Error -> updateErrorMessage(resource.errorMessage)

                        is Resource.Loading -> {
                            _registerState.update { currentState ->
                                currentState.copy(isLoading = resource.loading)
                            }
                        }

                        is Resource.Success -> {
                            _registerState.update { currentState ->
                                currentState.copy(
                                    isLoading = false,
                                    firebaseUser = resource.data,
                                    errorMessage = null
                                )
                            }
                            _uiEvent.emit(RegisterUiEvent.NavigateToLoginWithArgs(email, password))
                        }
                    }
                }
        }
    }

    private fun validateForm(
        userName: String,
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        val areFieldsEmpty = validationUseCase.fieldValidatorUseCase(
            listOf(
                userName,
                firstName,
                lastName,
                email,
                password,
                confirmPassword
            )
        )

        val doPasswordsMatch =
            validationUseCase.confirmPasswordValidatorUseCase(password, confirmPassword)

        val areFieldsValid = listOf(areFieldsEmpty, doPasswordsMatch).all { it }

        if (!areFieldsValid) {
            updateErrorMessage(message = "Fields are not valid!")
            return
        }

        if (!doPasswordsMatch) {
            updateErrorMessage(message = "Passwords do not match!")
            return
        }

        _registerState.update { it.copy(isLoading = true) }
        register(
            userName = userName,
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password
        )
    }

    private fun updateErrorMessage(message: String?) {
        _registerState.update { currentState -> currentState.copy(errorMessage = message) }
    }

    sealed interface RegisterUiEvent {
        data object NavigateToLogin : RegisterUiEvent
        data class NavigateToLoginWithArgs(val email: String, val password: String) :
            RegisterUiEvent
    }
}