package com.example.profile.screen

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.Resource
import com.core.domain.repository.league.LeagueRepository
import com.core.domain.use_case.auth.GetAuthUseCase
import com.core.domain.use_case.user.GetUserUseCase
import com.example.profile.event.ProfileEvent
import com.example.profile.mapper.auth.toPresenter
import com.example.profile.mapper.league.toPresentationModel
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
    private val getUserUseCase: GetUserUseCase,
    private val leagueRepository: LeagueRepository,
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

            ProfileEvent.LogOut -> logOut()
            ProfileEvent.FetchFavouriteLeagues -> fetchFavouriteLeagues()
        }
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            getUserUseCase.getCurrentUserUseCase().collect { resource ->
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
                                imageIsSet = true,
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
            getUserUseCase.getUploadProfileImageUseCase(userId, imageUri).collect { resource ->
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

    private fun fetchFavouriteLeagues() {
        viewModelScope.launch {
            leagueRepository.fetchFavouriteLeagues().collect { resource ->
                when (resource) {
                    is Resource.Error -> updateErrorMessage(resource.errorMessage)

                    is Resource.Loading -> {
                        _profileState.update { currentState ->
                            currentState.copy(isLoading = resource.loading)
                        }
                    }

                    is Resource.Success -> {
                        _profileState.update { it ->
                            it.copy(
                                leagues = resource.data.map { it.toPresentationModel() },
                                isLoading = false,
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