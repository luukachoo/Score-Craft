package com.core.data.service.send_notification

import com.core.data.model.send_notification.FCMResponseDTO
import com.core.data.model.send_notification.NotificationPayloadDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SendNotificationService {
    @Headers(
        "Authorization: key=AAAA46AqOVI:APA91bGdRSE96iKm04eryYYsFnnfn4e2HHstp4-_Hjk--QeY4FjnlFdrmifjfRO4LQF7snTbtHYBjpEyTHOx4fJLTq1vFtULnhnL-IAN7MEkA-yuue7aeY8GXYIrmRvwvkIlLH6otxky",
        "Content-Type:application/json"
    )
    @POST("fcm/send")
    suspend fun sendNotification(@Body payload: NotificationPayloadDTO): Response<FCMResponseDTO>
}
