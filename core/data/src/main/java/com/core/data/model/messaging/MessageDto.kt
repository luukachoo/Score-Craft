package com.core.data.model.messaging

data class MessageDto (
    val senderId: String? = null,
    val receiverId: String? = null,
    val messageText: String? = null,
    val timestamp: Long? = null,
    val messageId: String? = null
)