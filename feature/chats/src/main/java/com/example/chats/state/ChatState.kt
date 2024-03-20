package com.example.chats.state

import com.example.chats.model.Users

data class ChatState(
    val friends: List<Users>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val imageFetched: Boolean = false,
    val fcmToken: String? = null
)
