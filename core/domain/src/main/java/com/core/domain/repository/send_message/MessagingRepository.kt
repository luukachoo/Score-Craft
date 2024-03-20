package com.core.domain.repository.send_message

import com.core.common.resource.Resource
import com.core.domain.model.messaging.GetMessage
import kotlinx.coroutines.flow.Flow

interface MessagingRepository {
    suspend fun sendMessage(
        receiverId: String,
        messageText: String
    ): Flow<Resource<Unit>>

    fun fetchMessages(friendId: String): Flow<Resource<List<GetMessage>>>
}