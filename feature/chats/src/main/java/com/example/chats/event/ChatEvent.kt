package com.example.chats.event

sealed class ChatEvent {
    data object ResetErrorMessage : ChatEvent()
    data object FetchFriends : ChatEvent()
    data class AddFriend(val userName: String) : ChatEvent()
    data class SendFriendRequest(val fcmToken: String) : ChatEvent()
}