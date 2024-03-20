package com.core.domain.model.messaging

data class GetMessage(
    val senderId: String? = null,
    val receiverId: String? = null,
    val messageText: String? = null,
    val timestamp: Long? = null,
    val messageId: String? = null
)
