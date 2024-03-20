package com.core.domain.use_case.messaging

import com.core.domain.repository.send_message.MessagingRepository
import javax.inject.Inject

class GetSendMessageUseCase @Inject constructor(
    private val messagingRepository: MessagingRepository
) {
    suspend operator fun invoke(receiverId: String, messageText: String) = messagingRepository.sendMessage(receiverId, messageText)
}