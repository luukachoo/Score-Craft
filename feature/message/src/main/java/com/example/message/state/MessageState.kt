package com.example.message.state

import com.example.message.model.Message
import com.example.message.model.Users

data class MessageState(
    val user: Users? = null,
    val friend: Users? = null,
    val messages: List<Message>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val fcmToken: String? = null
)
