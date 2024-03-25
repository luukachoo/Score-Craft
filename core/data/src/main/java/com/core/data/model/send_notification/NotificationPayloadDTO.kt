package com.core.data.model.send_notification

data class NotificationPayloadDTO(
    val to: String,
    val notification: NotificationDataDTO
)
