package com.example.chats.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.Resource
import com.core.domain.use_case.friend.FriendsUseCase
import com.core.domain.use_case.send_notification.GetSendNotificationUseCase
import com.example.chats.event.ChatEvent
import com.example.chats.mapper.toPresenter
import com.example.chats.state.ChatState
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
class ChatFragmentViewModel @Inject constructor(
    private val friendsUseCase: FriendsUseCase,
    private val getSendNotificationUseCase: GetSendNotificationUseCase,
) : ViewModel() {
    private val _chatState = MutableStateFlow(ChatState())
    val chatState: StateFlow<ChatState> = _chatState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<ChatUiEvent>()
    val uiEvent: SharedFlow<ChatUiEvent> get() = _uiEvent

    fun onEvent(event: ChatEvent) {
        when (event) {
            is ChatEvent.AddFriend -> addFriend(event.userName)
            ChatEvent.FetchFriends -> fetchFriends()
            ChatEvent.ResetErrorMessage -> updateErrorMessage(message = null)
//            is ChatEvent.SendFriendRequest -> sendFriendRequest(event.fcmToken)
            is ChatEvent.OnFriendClick -> updateNavigationEvent(ChatUiEvent.NavigateToMessage(event.userId))
            ChatEvent.OnRequestClick -> updateNavigationEvent(ChatUiEvent.NavigateToFriendRequest)
            is ChatEvent.RemoveFriend -> removeFriend(event.friendId)
            else -> {}
        }
    }

    private fun addFriend(userName: String) {
        viewModelScope.launch {
            friendsUseCase.getAddFriendsUseCase(userName).collect { resource ->
                when (resource) {
                    is Resource.Error -> updateErrorMessage(resource.errorMessage)

                    is Resource.Loading -> {
                        _chatState.update { currentState ->
                            currentState.copy(
                                isLoading = resource.loading
                            )
                        }
                    }

                    is Resource.Success -> {
                        _chatState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                errorMessage = null
                            )
                        }
                        getFCMTokenForUser(userName)
                    }
                }
            }
        }
    }

    private fun fetchFriends() {
        viewModelScope.launch {
            friendsUseCase.getFetchFriendsUseCase().collect { resource ->
                when (resource) {
                    is Resource.Error -> updateErrorMessage(resource.errorMessage)

                    is Resource.Loading -> {
                        _chatState.update { currentState ->
                            currentState.copy(
                                isLoading = resource.loading
                            )
                        }
                    }

                    is Resource.Success -> {
                        _chatState.update { currentState ->
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

    private fun getFCMTokenForUser(userName: String) {
        viewModelScope.launch {
            friendsUseCase.getFCMTokenUseCase(userName).collect { resource ->
                when (resource) {
                    is Resource.Error -> updateErrorMessage(resource.errorMessage)

                    is Resource.Loading -> {
                        _chatState.update { currentState ->
                            currentState.copy(
                                isLoading = resource.loading
                            )
                        }
                    }

                    is Resource.Success -> {
                        _chatState.update { currentState ->
                            currentState.copy(
                                fcmToken = resource.data,
                                isLoading = false,
                                errorMessage = null
                            )
                        }
                    }
                }
            }
        }
    }

    private fun removeFriend(friendId: String) {
        viewModelScope.launch {
            friendsUseCase.getRemoveFriendUseCase(friendId).collect { resource ->
                when (resource) {
                    is Resource.Error -> updateErrorMessage(resource.errorMessage)

                    is Resource.Loading -> {
                        _chatState.update { currentState ->
                            currentState.copy(
                                isLoading = resource.loading
                            )
                        }
                    }

                    is Resource.Success -> {
                        _chatState.update { currentState ->
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

    private fun sendFriendRequest(fcmToken: String) {
        viewModelScope.launch {
            getSendNotificationUseCase(fcmToken).collect { resource ->
                when (resource) {
                    is Resource.Error -> updateErrorMessage(resource.errorMessage)

                    is Resource.Loading -> {
                        _chatState.update { currentState ->
                            currentState.copy(
                                isLoading = resource.loading
                            )
                        }
                    }

                    is Resource.Success -> {
                        _chatState.update { currentState ->
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

    private fun updateNavigationEvent(events: ChatUiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(events)
        }
    }

    private fun updateErrorMessage(message: String?) {
        _chatState.update { currentState -> currentState.copy(errorMessage = message) }
    }

    sealed interface ChatUiEvent {
        data class NavigateToMessage(val friendId: String) : ChatUiEvent
        data object NavigateToFriendRequest : ChatUiEvent
    }
}