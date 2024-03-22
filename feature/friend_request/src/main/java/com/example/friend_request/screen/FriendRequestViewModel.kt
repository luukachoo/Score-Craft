package com.example.friend_request.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.Resource
import com.core.domain.use_case.friend.FriendsUseCase
import com.example.friend_request.event.FriendRequestEvent
import com.example.friend_request.mapper.toPresenter
import com.example.friend_request.state.FriendRequestState
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
class FriendRequestViewModel @Inject constructor(
    private val friendsUseCase: FriendsUseCase
) : ViewModel() {
    private val _friendRequestState = MutableStateFlow(FriendRequestState())
    val friendRequestState: StateFlow<FriendRequestState> = _friendRequestState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<FriendRequestUiEvent>()
    val uiEvent: SharedFlow<FriendRequestUiEvent> get() = _uiEvent

    fun onEvent(event: FriendRequestEvent) {
        when (event) {
            is FriendRequestEvent.AcceptFriendRequest -> acceptFriendRequest(event.friendId)
            FriendRequestEvent.FetchFriends -> fetchFriendRequests()
            is FriendRequestEvent.RejectFriendRequest -> rejectFriendRequest(event.friendId)
            FriendRequestEvent.ResetErrorMessage -> updateErrorMessage(message = null)
            FriendRequestEvent.OnBackButtonClick -> updateNavigationEvent(FriendRequestUiEvent.NavigateToChats)
        }
    }

    private fun acceptFriendRequest(friendId: String) {
        viewModelScope.launch {
            friendsUseCase.getAcceptFriendRequestUseCase(friendId).collect { resource ->
                when (resource) {
                    is Resource.Error -> updateErrorMessage(resource.errorMessage)

                    is Resource.Loading -> {
                        _friendRequestState.update { currentState ->
                            currentState.copy(
                                isLoading = resource.loading
                            )
                        }
                    }

                    is Resource.Success -> {
                        _friendRequestState.update { currentState ->
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

    private fun rejectFriendRequest(friendId: String) {
        viewModelScope.launch {
            friendsUseCase.getRejectFriendRequestUseCase(friendId).collect { resource ->
                when (resource) {
                    is Resource.Error -> updateErrorMessage(resource.errorMessage)

                    is Resource.Loading -> {
                        _friendRequestState.update { currentState ->
                            currentState.copy(
                                isLoading = resource.loading
                            )
                        }
                    }

                    is Resource.Success -> {
                        _friendRequestState.update { currentState ->
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

    private fun fetchFriendRequests() {
        viewModelScope.launch {
            friendsUseCase.getFetchFriendRequestsUseCase().collect { resource ->
                when (resource) {
                    is Resource.Error -> updateErrorMessage(resource.errorMessage)

                    is Resource.Loading -> {
                        _friendRequestState.update { currentState ->
                            currentState.copy(
                                isLoading = resource.loading
                            )
                        }
                    }

                    is Resource.Success -> {
                        _friendRequestState.update { currentState ->
                            currentState.copy(
                                friends = resource.data.map { it.toPresenter() },
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
        _friendRequestState.update { currentState -> currentState.copy(errorMessage = message) }
    }

    private fun updateNavigationEvent(events: FriendRequestUiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(events)
        }
    }

    sealed interface FriendRequestUiEvent {
        data object NavigateToChats : FriendRequestUiEvent
    }
}