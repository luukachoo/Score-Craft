package com.core.data.mapper.send_notification

import com.core.data.model.send_notification.FCMResponseDTO
import com.core.data.model.send_notification.FCMResultDTO
import com.core.data.model.send_notification.NotificationDataDTO
import com.core.data.model.send_notification.NotificationPayloadDTO
import com.core.domain.model.send_notification.GetFCMResponse
import com.core.domain.model.send_notification.GetFCMResult
import com.core.domain.model.send_notification.GetNotificationData
import com.core.domain.model.send_notification.GetNotificationPayload

fun FCMResponseDTO.toDomain() = GetFCMResponse(
    success = success,
    failure = failure,
    results = results.map { it.toDomain() }
)

fun FCMResultDTO.toDomain() = GetFCMResult(
    messageId = messageId,
    error = error
)

fun NotificationDataDTO.toDomain() = GetNotificationData(
    title = title,
    body = body
)

fun NotificationPayloadDTO.toDomain() = GetNotificationPayload(
    to = to,
    notification = notification.toDomain()
)