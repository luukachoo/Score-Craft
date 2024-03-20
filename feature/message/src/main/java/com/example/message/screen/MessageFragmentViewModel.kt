package com.example.message.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.Resource
import com.core.domain.use_case.add_friend.FriendsUseCase
import com.core.domain.use_case.add_friend.GetAddFriendsUseCase
import com.core.domain.use_case.auth.GetAuthUseCase
import com.core.domain.use_case.messaging.MessagingUseCase
import com.example.message.event.MessageEvent
import com.example.message.mapper.toPresenter
import com.example.message.state.MessageState
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
class MessageFragmentViewModel @Inject constructor(
    private val messagingUseCase: MessagingUseCase,
    private val friendsUseCase: FriendsUseCase,
    private val getAuthUseCase: GetAuthUseCase
): ViewModel() {
    private val _messageState = MutableStateFlow(MessageState())
    val messageState: StateFlow<MessageState> = _messageState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<MessageUiEvent>()
    val uiEvent: SharedFlow<MessageUiEvent> get() = _uiEvent

    fun onEvent(event: MessageEvent) {
        when (event) {
            is MessageEvent.FetchFriend -> fetchFriend(event.friendId)
            is MessageEvent.SendMessage -> sendMessage(receiverId = event.receiverId, messageText = event.messageText)
            is MessageEvent.FetchMessages -> fetchMessages(event.friendId)
            MessageEvent.ResetErrorMessage -> updateErrorMessage(message = null)
            MessageEvent.GetCurrentUser -> getCurrentUser()
        }
    }

    private fun fetchFriend(friendId: String) {
        viewModelScope.launch {
            friendsUseCase.getFetchFriendWithId(friendId).collect { resource ->
                when (resource) {
                    is Resource.Error -> updateErrorMessage(message = resource.errorMessage)

                    is Resource.Loading -> {
                        _messageState.update { currentState ->
                            currentState.copy(
                                isLoading = resource.loading
                            )
                        }
                    }

                    is Resource.Success -> {
                        _messageState.update { currentState ->
                            currentState.copy(
                                friend = resource.data.toPresenter(),
                                isLoading = false,
                                errorMessage = null
                            )
                        }
                    }
                }
            }
        }
    }

    private fun sendMessage(receiverId: String, messageText: String) {
        viewModelScope.launch {
            messagingUseCase.getSendMessageUseCase(receiverId, messageText).collect { resource ->
                when (resource) {
                    is Resource.Error -> updateErrorMessage(message = resource.errorMessage)

                    is Resource.Loading -> {
                        _messageState.update { currentState ->
                            currentState.copy(
                                isLoading = resource.loading
                            )
                        }
                    }

                    is Resource.Success -> {
                        _messageState.update { currentState ->
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

    private fun fetchMessages(friendId: String) {
        viewModelScope.launch {
            messagingUseCase.getFetchMessagesUseCase(friendId).collect { resource ->
                when (resource) {
                    is Resource.Error -> updateErrorMessage(message = resource.errorMessage)

                    is Resource.Loading -> {
                        _messageState.update { currentState ->
                            currentState.copy(
                                isLoading = resource.loading
                            )
                        }
                    }

                    is Resource.Success -> {
                        _messageState.update { currentState ->
                            currentState.copy(
                                messages = resource.data.map { it.toPresenter() },
                                isLoading = false,
                                errorMessage = null
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            getAuthUseCase.getCurrentUserUseCase().collect { resource ->
                when (resource) {
                    is Resource.Error -> updateErrorMessage(message = resource.errorMessage)

                    is Resource.Loading -> {
                        _messageState.update { currentState ->
                            currentState.copy(
                                isLoading = resource.loading
                            )
                        }
                    }

                    is Resource.Success -> {
                        _messageState.update { currentState ->
                            currentState.copy(
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
        _messageState.update { currentState -> currentState.copy(errorMessage = message) }
    }

    sealed interface MessageUiEvent {
        data object NavigateToHome : MessageUiEvent
        data object NavigateToWelcome : MessageUiEvent
    }
}