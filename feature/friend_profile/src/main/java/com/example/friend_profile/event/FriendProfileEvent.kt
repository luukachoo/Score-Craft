package com.example.friend_profile.event

sealed class FriendProfileEvent {
    data object ResetErrorMessage : FriendProfileEvent()
    data class FetchFriendData(val friendId: String) : FriendProfileEvent()
    data object OnBackButtonClick : FriendProfileEvent()
    data class RemoveChatForCurrentUser(val friendId: String) : FriendProfileEvent()
    data class RemoveChatForBothUsers(val friendId: String) : FriendProfileEvent()
    data class FetchFriendFavouriteLeagues(val friendId: String) : FriendProfileEvent()
}