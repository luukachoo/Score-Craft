package com.core.domain.repository.send_notification

import com.core.common.resource.Resource
import com.core.domain.model.send_notification.GetFCMResponse
import kotlinx.coroutines.flow.Flow

interface SendNotificationRepository {
    suspend fun sendFriendRequestNotification(
        toToken: String,
    ): Flow<Resource<GetFCMResponse>>
}
