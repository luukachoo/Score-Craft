package com.example.friend_request.event

sealed class FriendRequestEvent {
    data object ResetErrorMessage : FriendRequestEvent()
    data object FetchFriends : FriendRequestEvent()
    data class AcceptFriendRequest(val friendId: String) : FriendRequestEvent()
    data class RejectFriendRequest(val friendId: String) : FriendRequestEvent()
}