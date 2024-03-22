package com.example.message.event

sealed class MessageEvent {
    data object ResetErrorMessage : MessageEvent()
    data class FetchFriend(val friendId: String) : MessageEvent()
    data class SendMessage(val receiverId: String, val messageText: String) : MessageEvent()
    data class FetchMessages(val friendId: String) : MessageEvent()
    data object OnBackButtonClick : MessageEvent()

    //    data class OnAvatarClick(val friendId: String) : MessageEvent()
    data object GetCurrentUser : MessageEvent()
}