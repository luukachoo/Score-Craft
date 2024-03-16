package com.core.domain.use_case.send_notification

import com.core.domain.repository.send_notification.SendNotificationRepository
import javax.inject.Inject

class GetSendNotificationUseCase @Inject constructor(
    private val sendNotificationRepository: SendNotificationRepository
) {
    suspend operator fun invoke(
        toToken: String,
    ) = sendNotificationRepository.sendFriendRequestNotification(
        toToken = toToken,
    )
}