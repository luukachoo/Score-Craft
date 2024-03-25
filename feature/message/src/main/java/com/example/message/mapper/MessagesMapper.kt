package com.example.message.mapper

import com.core.domain.model.messaging.GetMessage
import com.example.message.model.Message

fun GetMessage.toPresenter() = Message(
    senderId = senderId,
    receiverId = receiverId,
    messageText = messageText,
    timestamp = timestamp,
    messageId = messageId
)