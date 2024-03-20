package com.core.domain.model.send_notification

data class GetNotificationPayload(
    val to: String,
    val notification: GetNotificationData
)
