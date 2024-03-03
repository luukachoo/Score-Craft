package com.example.profile.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.Resource
import com.core.domain.use_case.auth.GetAuthUseCase
import com.example.profile.event.ProfileEvent
import com.example.profile.mapper.auth.toPresenter
import com.example.profile.state.ProfileState
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
class ProfileFragmentViewModel @Inject constructor(
    private val getAuthUseCase: GetAuthUseCase
): ViewModel() {
    private val _profileState = MutableStateFlow(ProfileState())
    val profileState: StateFlow<ProfileState> = _profileState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<ProfileUiEvent>()
    val uiEvent: SharedFlow<ProfileUiEvent> get() = _uiEvent

    fun onEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.GetCurrentUser -> getCurrentUser()
            ProfileEvent.ResetErrorMessage -> updateErrorMessage(message = null)
        }
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            getAuthUseCase.getCurrentUserUseCase().collect { resource ->
                when (resource) {
                    is Resource.Error -> updateErrorMessage(resource.errorMessage)

                    is Resource.Loading -> {
                        _profileState.update { currentState ->
                            currentState.copy(isLoading = resource.loading)
                        }
                    }

                    is Resource.Success -> {
                        _profileState.update {
                            it.copy(
                                user = resource.data.toPresenter(),
                                isLoading = false,
                                errorMessage = null
                            )
                        }
                    }
                }
            }
        }
    }

    private fun updateErrorMessage(message: String?) {
        _profileState.update { currentState -> currentState.copy(errorMessage = message) }
    }

    sealed interface ProfileUiEvent {
        data object NavigateToHome : ProfileUiEvent
    }
}