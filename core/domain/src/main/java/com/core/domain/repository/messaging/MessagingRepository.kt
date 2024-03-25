package com.core.domain.repository.messaging

import com.core.common.resource.Resource
import com.core.domain.model.messaging.GetMessage
import kotlinx.coroutines.flow.Flow

interface MessagingRepository {
    suspend fun sendMessage(
        receiverId: String,
        messageText: String
    ): Flow<Resource<Unit>>

    fun fetchMessages(friendId: String): Flow<Resource<List<GetMessage>>>
    suspend fun removeChatForCurrentUser(friendId: String): Flow<Resource<Unit>>
    suspend fun removeChatForBothUsers(friendId: String): Flow<Resource<Unit>>
}