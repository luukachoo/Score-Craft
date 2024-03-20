package com.example.message.model

data class Message(
    val senderId: String? = null,
    val receiverId: String? = null,
    val messageText: String? = null,
    val timestamp: Long? = null,
    val messageId: String? = null
)