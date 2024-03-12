package com.example.profile.screen

import android.net.Uri
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
) : ViewModel() {
    private val _profileState = MutableStateFlow(ProfileState())
    val profileState: StateFlow<ProfileState> = _profileState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<ProfileUiEvent>()
    val uiEvent: SharedFlow<ProfileUiEvent> get() = _uiEvent

    fun onEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.GetCurrentUser -> getCurrentUser()
            ProfileEvent.ResetErrorMessage -> updateErrorMessage(message = null)
            is ProfileEvent.UploadProfileImage -> uploadProfileImage(
                userId = event.userId,
                imageUri = event.imageUri
            )

            is ProfileEvent.FetchUserProfileImage -> fetchUserProfileImage(event.userId)
            ProfileEvent.LogOut -> logOut()
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

    private fun uploadProfileImage(userId: String, imageUri: Uri) {
        viewModelScope.launch {
            getAuthUseCase.getUploadProfileImageUseCase(userId, imageUri).collect { resource ->
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
                                isLoading = false,
                                imageUploaded = true,
                                imageIsSet = true,
                                errorMessage = null
                            )
                        }
                    }
                }
            }
        }
    }

    private fun fetchUserProfileImage(userId: String) {
        viewModelScope.launch {
            getAuthUseCase.getUserProfileImageUseCase(userId).collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _profileState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                imageFetched = true
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _profileState.update { currentState ->
                            currentState.copy(
                                isLoading = resource.loading,
                                imageFetched = true
                            )
                        }
                    }

                    is Resource.Success -> {
                        _profileState.update { currentState ->
                            currentState.copy(
                                imageUri = resource.data,
                                isLoading = false,
                                imageFetched = true,
                                errorMessage = null
                            )
                        }
                    }
                }
            }
        }
    }

    private fun logOut() {
        viewModelScope.launch {
            getAuthUseCase.getLogOutUseCase()
            _uiEvent.emit(ProfileUiEvent.NavigateToWelcome)
        }
    }

    private fun updateErrorMessage(message: String?) {
        _profileState.update { currentState -> currentState.copy(errorMessage = message) }
    }

    sealed interface ProfileUiEvent {
        data object NavigateToHome : ProfileUiEvent
        data object NavigateToWelcome : ProfileUiEvent
    }
}