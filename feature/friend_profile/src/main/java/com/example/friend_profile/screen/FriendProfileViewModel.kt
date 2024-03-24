package com.example.friend_profile.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.Resource
import com.core.domain.use_case.friend.FriendsUseCase
import com.core.domain.use_case.leagues.LeagueUseCase
import com.core.domain.use_case.messaging.MessagingUseCase
import com.example.friend_profile.event.FriendProfileEvent
import com.example.friend_profile.mapper.league.toPresentationModel
import com.example.friend_profile.mapper.user.toDomain
import com.example.friend_profile.state.FriendProfileState
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
class FriendProfileViewModel @Inject constructor(
    private val messagingUseCase: MessagingUseCase,
    private val friendsUseCase: FriendsUseCase,
    private val leagueUseCase: LeagueUseCase
) : ViewModel() {
    private val _friendProfileState = MutableStateFlow(FriendProfileState())
    val friendProfileState: StateFlow<FriendProfileState> = _friendProfileState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<FriendProfileUiEvent>()
    val uiEvent: SharedFlow<FriendProfileUiEvent> get() = _uiEvent

    fun onEvent(event: FriendProfileEvent) {
        when (event) {
            FriendProfileEvent.OnBackButtonClick -> updateNavigationEvent(FriendProfileUiEvent.NavigateToMessage)
            FriendProfileEvent.ResetErrorMessage -> updateErrorMessage(message = null)
            is FriendProfileEvent.FetchFriendData -> fetchFriend(event.friendId)
            is FriendProfileEvent.RemoveChatForCurrentUser -> removeChatForCurrentUser(event.friendId)
            is FriendProfileEvent.RemoveChatForBothUsers -> removeChatForBothUsers(event.friendId)
            is FriendProfileEvent.FetchFriendFavouriteLeagues -> fetchFriendFavouriteLeagues(event.friendId)
        }
    }

    private fun fetchFriend(friendId: String) {
        viewModelScope.launch {
            friendsUseCase.getFetchFriendWithId(friendId).collect { resource ->
                when (resource) {
                    is Resource.Error -> updateErrorMessage(resource.errorMessage)

                    is Resource.Loading -> {
                        _friendProfileState.update { currentState ->
                            currentState.copy(
                                isLoading = resource.loading
                            )
                        }
                    }

                    is Resource.Success -> {
                        _friendProfileState.update { currentState ->
                            currentState.copy(
                                friend = resource.data.toDomain(),
                                isLoading = false,
                                errorMessage = null
                            )
                        }
                    }
                }
            }
        }
    }

    private fun fetchFriendFavouriteLeagues(friendId: String) {
        viewModelScope.launch {
            leagueUseCase.getFetchFriendFavouriteLeagueUseCase(friendId).collect { resource ->
                when (resource) {
                    is Resource.Error -> updateErrorMessage(resource.errorMessage)

                    is Resource.Loading -> {
                        _friendProfileState.update { currentState ->
                            currentState.copy(
                                isLoading = resource.loading
                            )
                        }
                    }

                    is Resource.Success -> {
                        _friendProfileState.update { currentState ->
                            currentState.copy(
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

    private fun removeChatForCurrentUser(friendId: String) {
        viewModelScope.launch {
            messagingUseCase.getRemoveChatForCurrentUseCase(friendId).collect { resource ->
                when (resource) {
                    is Resource.Error -> updateErrorMessage(resource.errorMessage)

                    is Resource.Loading -> {
                        _friendProfileState.update { currentState ->
                            currentState.copy(
                                isLoading = resource.loading
                            )
                        }
                    }

                    is Resource.Success -> {
                        _friendProfileState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                errorMessage = null
                            )
                        }
                    }
                }
            }
        }
    }

    private fun removeChatForBothUsers(friendId: String) {
        viewModelScope.launch {
            messagingUseCase.getRemoveChatForBothUseCase(friendId).collect { resource ->
                when (resource) {
                    is Resource.Error -> updateErrorMessage(resource.errorMessage)

                    is Resource.Loading -> {
                        _friendProfileState.update { currentState ->
                            currentState.copy(
                                isLoading = resource.loading
                            )
                        }
                    }

                    is Resource.Success -> {
                        _friendProfileState.update { currentState ->
                            currentState.copy(
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
        _friendProfileState.update { currentState -> currentState.copy(errorMessage = message) }
    }

    private fun updateNavigationEvent(events: FriendProfileUiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(events)
        }
    }

    sealed interface FriendProfileUiEvent {
        data object NavigateToMessage : FriendProfileUiEvent
    }
}