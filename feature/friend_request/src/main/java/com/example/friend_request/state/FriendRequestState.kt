package com.example.friend_request.state

import com.example.friend_request.model.Users

data class FriendRequestState(
    val friends: List<Users>? = null,
    val friend: Users? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)