package com.core.domain.use_case.messaging

import com.core.domain.repository.messaging.MessagingRepository
import javax.inject.Inject

class GetRemoveChatForBothUseCase @Inject constructor(
    private val messagingRepository: MessagingRepository
) {
    suspend operator fun invoke(friendId: String) = messagingRepository.removeChatForBothUsers(friendId)
}