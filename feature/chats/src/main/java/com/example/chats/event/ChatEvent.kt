package com.example.chats.event

sealed class ChatEvent {
    data object ResetErrorMessage : ChatEvent()
    data object FetchFriends : ChatEvent()
    data object OnRequestClick : ChatEvent()
    data class AddFriend(val userName: String) : ChatEvent()
    data class OnFriendClick(val userId: String) : ChatEvent()
    data class RemoveFriend(val friendId: String) : ChatEvent()
    data class SendFriendRequest(val fcmToken: String) : ChatEvent()
}