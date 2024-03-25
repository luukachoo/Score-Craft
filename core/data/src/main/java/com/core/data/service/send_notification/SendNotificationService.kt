package com.core.data.service.send_notification

import com.core.data.model.send_notification.FCMResponseDTO
import com.core.data.model.send_notification.NotificationPayloadDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface SendNotificationService {
    @Headers("Content-Type: application/json")
    @POST("send")
    suspend fun sendNotification(
        @Header("Authorization") authorization: String,
        @Body payload: NotificationPayloadDTO
    ): Response<FCMResponseDTO>
}
